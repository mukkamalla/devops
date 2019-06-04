package com.valign.payroll.webservice.util;

import java.io.IOException;
import java.util.Random;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valign.payroll.webservice.dto.AuthCheck;
import com.valign.payroll.webservice.dto.PayrollRegisteredUsers;
import com.valign.payroll.webservice.dto.PayrollUnRegisteredUsers;
import com.valign.payroll.webservice.jwt.JwtTokenProvider;
import com.valign.payroll.webservice.jwt.Role;
import com.valign.payroll.webservice.model.Company;
import com.valign.payroll.webservice.model.CompanyGroup;
import com.valign.payroll.webservice.model.Employee;
import com.valign.payroll.webservice.model.InputActivationUser;
import com.valign.payroll.webservice.model.PayrollUser;
import com.valign.payroll.webservice.service.PayrollUserService;

import com.valign.payroll.webservice.service.EmployeeService;
import com.valign.payroll.webservice.service.CompanyGroupService;
import com.valign.payroll.webservice.service.CompanyService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import reactor.core.publisher.Mono;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PayrollLoginServiceController {


    @Autowired
    private EmailService emailService;
	@Autowired
	private ReactiveMongoOperations operations;
	@Autowired
	private PasswordEncryption passwordEncryption;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private PayrollUserService userService;

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CompanyGroupService companyGroupService;
	@Autowired
	private CompanyService companyService;

	@Value("${imgpath}")
	private String imgpath;
	@Value("${activationUrl}")
	private String activationUrl;
	@Value("${loginUrl}")
	private String loginUrl;

	@PostMapping("/authorize")
	public Mono<AuthCheck> postAuthorize(@RequestBody AuthCheck authcheck, @RequestHeader Map<String, String> header) {
		Optional<String> token = WebUtils.extractToken(header);
		Query query = new Query();
		query.addCriteria(Criteria.where("salt").is(authcheck.getHash()));
		return this.operations.findOne(query, PayrollRegisteredUsers.class)
				.switchIfEmpty(Mono.just(new PayrollRegisteredUsers())).map(user -> mapMyUser(user, authcheck, token));
	}

	private AuthCheck mapMyUser(PayrollRegisteredUsers myUser, AuthCheck authcheck, Optional<String> token) {
		Optional<Jws<Claims>> claims = this.jwtTokenProvider.getClaims(token);
		if (myUser.getUserId() != null && claims.isPresent()
				&& myUser.getUserId().equals(claims.get().getBody().getSubject())
				&& new Date().before(claims.get().getBody().getExpiration())) {
			return new AuthCheck(authcheck.getHash(), authcheck.getPath(), true);
		}
		return new AuthCheck(authcheck.getHash(), authcheck.getPath(), false);
	}
	
	@PostMapping("/activate")
	public Mono<InputActivationUser> postUserSigninActivate(@RequestBody InputActivationUser inputActivationUser)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		System.out.println("calling Activation for Subscription user service..." + " filename "
				+ inputActivationUser.getUserId()+ " " + inputActivationUser.getOtp() + " "
				);
		
		PayrollRegisteredUsers payrollUser = new PayrollRegisteredUsers();
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(inputActivationUser.getUserId()));
		PayrollUnRegisteredUsers user = this.operations.findOne(query, PayrollUnRegisteredUsers.class)
				.switchIfEmpty(Mono.just(new PayrollUnRegisteredUsers())).block();
		if ((user.getUserId() != null) && (user.getConfirmSubscriptionOtp().equals(inputActivationUser.getOtp()))) {
			payrollUser.setAddress1(user.getAddress1());
			payrollUser.setAddress2(user.getAddress2());
			payrollUser.setAvatarValue(user.getAvatarValue());
			payrollUser.setCity(user.getCity());
			payrollUser.setCompanyId(user.getCompanyId());
			payrollUser.setCompanyName(user.getCompanyName());
			payrollUser.setCountry(user.getCountry());
			payrollUser.setEmail(user.getEmail());
			payrollUser.setFirstName(user.getFirstName());
			payrollUser.setLastName(user.getLastName());
			
			payrollUser.setPassword(user.getPassword());
			payrollUser.setPayrollUserRole(user.getPayrollUserRole());
			payrollUser.setPostalCode(user.getPostalCode());
			
			payrollUser.setSalt(user.getSalt());
			payrollUser.setState(user.getState());
			payrollUser.setToken(user.getToken());
			payrollUser.setUserId(user.getUserId());
			this.operations.save(payrollUser).block();
		

			// send Email

			Mail mail = new Mail();
			mail.setFrom("donotreply.valign.payroll@gmail.com");
			mail.setTo(user.getEmail());
			mail.setSubject("v-align Payroll Application Subscription Confirmation");

			Map model = new java.util.HashMap();
			model.put("name", user.getFirstName() + " " + user.getLastName());
			model.put("userId", user.getUserId());
			model.put("loginUrl", loginUrl);
			mail.setModel(model);

			try {
				emailService.sendSimpleConfirmMessage(mail);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			return Mono.just(inputActivationUser);
		}

		return Mono.just(new InputActivationUser());
	}

	@PostMapping("/signup")
	public Mono<InputSubscriptionUser> postUserSignin(@RequestBody InputSubscriptionUser inputSubscriptionUser)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		System.out.println("calling Sign Up for Subscription user service..." + " filename "
				+ inputSubscriptionUser.getAvatarFileName() + " " + inputSubscriptionUser.getAvatarFileType() + " "
				+ inputSubscriptionUser.getAvatarValue() + "  :::::::::  " + inputSubscriptionUser.getFirstName() + " "
				+ inputSubscriptionUser.getEmailId() + " " + inputSubscriptionUser.getLastName() + " "
				+ inputSubscriptionUser.getCountryId() + " " + inputSubscriptionUser.getFinancialYearStart() + " "
				+ inputSubscriptionUser.getTimeZoneId() + " " + inputSubscriptionUser.getBaseCurrencyId());
		String finStartDate = inputSubscriptionUser.getFinancialYearStart();
		finStartDate = finStartDate.replace('T', ' ');
		Date finStartDate1 = null;
		try {
			SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

			date2.setTimeZone(TimeZone.getTimeZone("GMT"));
			finStartDate1 = date2.parse(finStartDate);

		} catch (ParseException ex) {
			// Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println(finStartDate + "\t" + finStartDate1);

		java.util.Date creationDate = new java.util.Date();
		CompanyGroup companyGroup = new CompanyGroup();
		companyGroup.setCompanyCount(1);

		companyGroup.setCreationBy(1);
		companyGroup.setCreationDate(creationDate);
		companyGroup.setUpdatedBy(1);
		companyGroup.setUpdatedDate(creationDate);
		companyGroup.setRecordStatus(1);
		CompanyGroup companyGroupSaved = companyGroupService.create(companyGroup);
		System.out.println("company group name is " + inputSubscriptionUser.getCompanyGroupName());
		if (inputSubscriptionUser.getCompanyGroupName().equals("")) {
			companyGroup.setCompanyGroupName(inputSubscriptionUser.getCompanyName());
		} else {
			System.out.println("company group name is not empty");
			companyGroup.setCompanyGroupName(inputSubscriptionUser.getCompanyGroupName());
		}

		// Insert a new row in comp table
		Company company = new Company();
		company.setAddress1(inputSubscriptionUser.getAddress1());
		company.setAddress2(inputSubscriptionUser.getAddress2());
		company.setBaseCurrencyId(inputSubscriptionUser.getBaseCurrencyId());
		company.setCity(inputSubscriptionUser.getCity());
		company.setState(inputSubscriptionUser.getState());
		company.setPostalCode(inputSubscriptionUser.getPostalCode());
		company.setCompanyGroupId(companyGroupSaved.getId());
		company.setCompanyName(inputSubscriptionUser.getCompanyName());
		company.setCountryId(inputSubscriptionUser.getCountryId());
		company.setTimeZoneId(inputSubscriptionUser.getTimeZoneId());
		company.setRecordStatus(1);
		company.setCreationBy(1);
		company.setCreationDate(creationDate);
		company.setUpdatedBy(1);
		company.setUpdatedDate(creationDate);

		finStartDate = inputSubscriptionUser.getFinancialYearStart();
		finStartDate = finStartDate.replace('T', ' ');
		finStartDate1 = null;
		try {
			SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

			date2.setTimeZone(TimeZone.getTimeZone("GMT"));
			finStartDate1 = date2.parse(finStartDate);

		} catch (ParseException ex) {
			// Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println(finStartDate + "\t" + finStartDate1);
		company.setFinancialYearStart(finStartDate1);
		company.setKeyContactEmailId(inputSubscriptionUser.getKeyContactEmailId());
		company.setKeyContactName(inputSubscriptionUser.getKeyContactName());
		company.setKeyContactPhone(inputSubscriptionUser.getKeyContactPhone());
		Company companySaved = companyService.create(company);

		// Insert a new row in emp table
		Employee employee = new Employee();
		employee.setRecordStatus(1);
		employee.setCreationBy(1);
		employee.setCompanyId(companySaved.getId());
		employee.setCreationDate(creationDate);
		employee.setUpdatedBy(1);
		employee.setUpdatedDate(creationDate);
		employee.setAadharNumber(null);
		employee.setBankAccountNumber(null);
		employee.setBankIFSCCode(null);
		employee.setCompanyId(companySaved.getId());
		employee.setCreationBy(1);
		employee.setDateOfBirth(null);
		employee.setDateOfJoining(null);
		employee.setDepartmentId(1);
		employee.setDesignationId(1);
		employee.setEmailId(inputSubscriptionUser.getEmailId());
		employee.setEmployeeCode(null);
		employee.setEmpStatus(1);
		employee.setFirstName(inputSubscriptionUser.getFirstName());
		employee.setGradeId(1);
		employee.setLastName(inputSubscriptionUser.getLastName());
		employee.setLocationId(1);
		employee.setMiddleName(null);
		employee.setMobileNumber(null);
		employee.setPanNumber(null);
		employee.setPermAddress1(null);
		employee.setPermAddress2(null);
		employee.setPermCity(null);
		employee.setPermCountryId(104);
		employee.setPermPostalCode(null);
		employee.setPermState(null);
		employee.setPhoneNumber(null);
		employee.setTempPostalCode(null);
		employee.setTempAddress1(null);
		employee.setTempAddress2(null);
		employee.setTempCity(null);
		employee.setTempCountryId(104);
		employee.setTempPostalCode(null);
		employee.setTempState(null);
		employee.setUpdatedBy(1);
		employee.setUpdatedDate(creationDate);
		employee.setSalutation(1);

		Employee employeeSaved = employeeService.create(employee);

		// Insert a new row in table pu (Payroll User as the System Administrator user
		// with Admin previledges
		PayrollUnRegisteredUsers payrollUser = new PayrollUnRegisteredUsers();

		payrollUser.setEmail(inputSubscriptionUser.getEmailId());
		payrollUser.setFirstName(inputSubscriptionUser.getFirstName());
		payrollUser.setLastName(inputSubscriptionUser.getLastName());
		payrollUser.setPassword(inputSubscriptionUser.getPassword());
		payrollUser.setAvatarValue(inputSubscriptionUser.getAvatarValue());
		payrollUser.setAddress1(inputSubscriptionUser.getAddress1());
		payrollUser.setAddress2(inputSubscriptionUser.getAddress2());
		payrollUser.setCity(inputSubscriptionUser.getCity());
		payrollUser.setCompanyName(inputSubscriptionUser.getCompanyName());
		payrollUser.setCountry(inputSubscriptionUser.getCountryId());
		payrollUser.setPostalCode(inputSubscriptionUser.getPostalCode());
		payrollUser.setState(inputSubscriptionUser.getState());
		payrollUser.setPayrollUserRole(1);
		payrollUser.setCompanyId(companySaved.getId());

		Random rand = new Random();
		int confirmSubscriptionOtp = rand.nextInt(999998) + 1;
		Integer otp = new Integer(confirmSubscriptionOtp);
		payrollUser.setConfirmSubscriptionOtp(otp.toString());

		payrollUser.setUserId(inputSubscriptionUser.getUserId());

		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(inputSubscriptionUser.getUserId()));
		PayrollUnRegisteredUsers user = this.operations.findOne(query, PayrollUnRegisteredUsers.class)
				.switchIfEmpty(Mono.just(new PayrollUnRegisteredUsers())).block();
		if (user.getUserId() == null) {
			String salt = this.passwordEncryption.generateSalt();
			String encryptedPassword = this.passwordEncryption.getEncryptedPassword(payrollUser.getPassword(), salt);
			payrollUser.setPassword(encryptedPassword);
			payrollUser.setSalt(salt);
			this.operations.save(payrollUser).block();
			inputSubscriptionUser.setPassword(encryptedPassword);
			inputSubscriptionUser.setSalt(salt);

			PayrollUser payrollUser1 = new PayrollUser();

			payrollUser1.setEmailId(inputSubscriptionUser.getEmailId());
			payrollUser1.setFirstName(inputSubscriptionUser.getFirstName());
			payrollUser1.setLastName(inputSubscriptionUser.getLastName());

			payrollUser1.setPassword(inputSubscriptionUser.getPassword());
			payrollUser1.setPayrollUserRole(1);
			payrollUser1.setUserId(inputSubscriptionUser.getUserId());
			payrollUser1.setSalt(salt);
			payrollUser1.setRecordStatus(1);
			payrollUser1.setDefaultLocaleId(3);
			payrollUser1.setCompanyId(companySaved.getId());
			payrollUser1.setCreationBy(1);
			payrollUser1.setCreationDate(creationDate);
			payrollUser1.setUpdatedBy(1);
			payrollUser1.setUpdatedDate(creationDate);
			userService.createNewSubscription(payrollUser1, payrollUser.getToken());

			// send Email

			Mail mail = new Mail();
			mail.setFrom("donotreply.valign.payroll@gmail.com");
			mail.setTo(inputSubscriptionUser.getEmailId());
			mail.setSubject("v-align Payroll Application Subscription");

			Map

			model = new java.util.HashMap();
			model.put("name", inputSubscriptionUser.getFirstName() + " " + inputSubscriptionUser.getLastName());
			model.put("userId", inputSubscriptionUser.getUserId());
			String newOtp = otp.toString();
			System.out.println("new OTP is " +newOtp);
			model.put("otp", newOtp);
			model.put("activationUrl", activationUrl);
			mail.setModel(model);

			try {
				emailService.sendSimpleMessage(mail);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return Mono.just(inputSubscriptionUser);
		}

		return Mono.just(new InputSubscriptionUser());
	}

	
	@PostMapping("/logout")
	public Mono<PayrollRegisteredUsers> postLogout(@RequestBody String hash, HttpServletRequest request) {
		Query query = new Query();
		query.addCriteria(Criteria.where("salt").is(hash));
		return this.operations.findOne(query, PayrollRegisteredUsers.class)
				.switchIfEmpty(Mono.just(new PayrollRegisteredUsers())).map(user1 -> loginHelp(user1, ""));
	}

	@PostMapping("/login")
	public Mono<PayrollRegisteredUsers> postUserLogin(@RequestBody PayrollRegisteredUsers myUser,
			HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
		System.out.println("request for login came in " + myUser.getUserId() + " " + myUser.getPassword());
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(myUser.getUserId()));
		return this.operations.findOne(query, PayrollRegisteredUsers.class)
				.switchIfEmpty(Mono.just(new PayrollRegisteredUsers()))
				.map(user1 -> loginHelp(user1, myUser.getPassword()));
	}

	private PayrollRegisteredUsers loginHelp(PayrollRegisteredUsers user, String passwd) {
		if (user.getUserId() != null) {
			String encryptedPassword;
			try {
				encryptedPassword = this.passwordEncryption.getEncryptedPassword(passwd, user.getSalt());
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				System.out.println("no such user exception");
				return new PayrollRegisteredUsers();
			}
			if (user.getPassword().equals(encryptedPassword)) {
				System.out.println("login successs password matched !!!");
				String jwtToken = this.jwtTokenProvider.createToken(user.getUserId(), Arrays.asList(Role.USERS));
				user.setToken(jwtToken);
				user.setPassword("XXX");
				return user;
			}
		}
		System.out.println("invalid password did not match  ");
		return new PayrollRegisteredUsers();
	}
}