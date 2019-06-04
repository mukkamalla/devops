package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.valign.payroll.webservice.dto.PayrollRegisteredUsers;
import com.valign.payroll.webservice.model.PayrollUser;
import com.valign.payroll.webservice.repository.PayrollUserRepository;
import com.valign.payroll.webservice.service.PayrollUserService;
import com.valign.payroll.webservice.util.PasswordEncryption;
import com.valign.payroll.webservice.util.SearchCriteria;
import com.valign.payroll.webservice.util.UserSearchQueryCriteriaConsumer;

import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.List;

@Service
public class PayrollUserServiceImpl implements PayrollUserService {

	@Value("${security.jwt.token.secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length}")
	private long validityInMilliseconds; // 24h

	@Autowired
	private PayrollUserRepository repository;
	@Autowired
	private PayrollUserRepository crudRepository;

	@Autowired
	private PasswordEncryption passwordEncryption;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ReactiveMongoOperations operations;

	@Override
	public PayrollUser create(PayrollUser payrollUser, String authtoken) {

		System.out.println("calling Sign Up for Subscription user service..." + payrollUser.getFirstName() + " "
				+ payrollUser.getEmailId() + " " + payrollUser.getLastName());

		PayrollRegisteredUsers myUser = new PayrollRegisteredUsers();

		myUser.setEmail(payrollUser.getEmailId());
		myUser.setFirstName(payrollUser.getFirstName());
		myUser.setLastName(payrollUser.getLastName());
		myUser.setPassword(payrollUser.getPassword());

		myUser.setUserId(payrollUser.getUserId());

		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(payrollUser.getUserId()));
		PayrollRegisteredUsers user = this.operations.findOne(query, PayrollRegisteredUsers.class)
				.switchIfEmpty(Mono.just(new PayrollRegisteredUsers())).block();
		if (user.getUserId() == null) {
			String salt = null;
			try {
				salt = this.passwordEncryption.generateSalt();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String encryptedPassword = null;
			String defaultPassword = "welcome123";
			try {
				encryptedPassword = this.passwordEncryption.getEncryptedPassword(defaultPassword, salt);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myUser.setPassword(encryptedPassword);
			myUser.setSalt(salt);
			this.operations.save(myUser).block();
			payrollUser.setPassword(encryptedPassword);
			payrollUser.setSalt(salt);
			crudRepository.save(payrollUser);
			// userService.create(payrollUser, myUser.getToken());
			return payrollUser;
		}
		return new PayrollUser();

	}

	@Override
	public PayrollUser createNewSubscription(PayrollUser payrollUser, String authtoken) {

		System.out.println("userid " + payrollUser.getUserId());

		System.out.println("fname " + payrollUser.getFirstName());
		System.out.println("lastName " + payrollUser.getLastName());

		java.util.Date creationDate = new java.util.Date();
		payrollUser.setCreationDate(creationDate);
		if (!(payrollUser.getPassword() != null)) {
			System.out.println("setting default password........");
			payrollUser.setPassword("welcome123");

			System.out.println("the value of createdBy is 2 ");
			String salt = null;
			try {
				salt = this.passwordEncryption.generateSalt();
				System.out.println("the value of createdBy is 3 ");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				System.out.println("the value of createdBy is 4 ");
				e.printStackTrace();
			}
			String encryptedPassword = null;
			try {
				System.out.println("the value of createdBy is 5 ");
				encryptedPassword = this.passwordEncryption.getEncryptedPassword(payrollUser.getPassword(), salt);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("the value of createdBy is 6 ");

			System.out.println("the value of createdBy is 7 ");

			payrollUser.setPassword(encryptedPassword);
			payrollUser.setSalt(salt);

			String createdBy = "Subscription App";
			if (authtoken != null) {

				System.out.println("no token during subscription");
				Query query1 = new Query();
				query1.addCriteria(Criteria.where("userId").is(getUsername(authtoken)));
				PayrollRegisteredUsers user1 = operations.findOne(query1, PayrollRegisteredUsers.class).block();
				createdBy = user1.getUserId();
			}
			System.out.println("the value of createdBy is " + createdBy);
			// payrollUser.setCreationBy(createdBy);
		}
		return crudRepository.save(payrollUser);

	}

	public String getUsername(String token) {
		String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		return Jwts.parser().setSigningKey(encodedSecretKey).parseClaimsJws(token).getBody().getSubject();
	}

	@Override
	public PayrollUser delete(int id) {
		PayrollUser user = findById(id);
		if (user != null) {
			repository.delete(user);
		}
		return user;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<PayrollUser> findAllSortOnColumn(String pageSize, String pageNumber, String columnName,
			String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {

			return (Page<PayrollUser>) repository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<PayrollUser>) repository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<PayrollUser> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<PayrollUser>) repository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public PayrollUser findById(int id) {
		return repository.findById(id);
	}

	@Override
	public List<PayrollUser> findByUserId(String userId) {
		return repository.findByUserIdContains(userId);
	}

	@Override
	public PayrollUser update(PayrollUser user) {
		return repository.save(user);
	}
}
