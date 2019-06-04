package com.valign.payroll.webservice.serviceimpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.valign.payroll.webservice.model.PayrollUserRole;
import com.valign.payroll.webservice.model.Role;
import com.valign.payroll.webservice.repository.PayrollUserRoleRepository;
import com.valign.payroll.webservice.service.PayrollUserRoleService;
import com.valign.payroll.webservice.util.PasswordEncryption;
import com.valign.payroll.webservice.util.SearchCriteria;
import com.valign.payroll.webservice.util.UserSearchQueryCriteriaConsumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class PayrollUserRoleServiceImpl implements PayrollUserRoleService {

	@Autowired
	private PayrollUserRoleRepository repository;
	//@Autowired
	//private PayrollUserRepository crudRepository;
	
	@Autowired
	private PasswordEncryption passwordEncryption;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public PayrollUserRole create(PayrollUserRole payrollUserRole) {

		System.out.println("email " + payrollUserRole.getRoleName());

		System.out.println("fname " + payrollUserRole.getRoleDesc());
		
		return repository.save(payrollUserRole);
	}

	@Override
	public PayrollUserRole delete(int id) {
		PayrollUserRole payrollUserRole = findById(id);
		if (payrollUserRole != null) {
			repository.delete(payrollUserRole);
		}
		return payrollUserRole;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<PayrollUserRole> findAllSortOnColumn(String pageSize, String pageNumber, String columnName,
			String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {

			return (Page<PayrollUserRole>) repository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<PayrollUserRole>) repository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}


	@SuppressWarnings("deprecation")
	@Override
	public Page<PayrollUserRole> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<PayrollUserRole>) repository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public PayrollUserRole findById(int id) {
		return repository.findById(id);
	}

	@Override
	public PayrollUserRole update(PayrollUserRole user) {
		return repository.save(user);
	}
	@Override
	public Integer countRecord() {

		return (Integer) repository.countRecords();

	}

	public java.util.List<PayrollUserRole> findAll(){
		return (java.util.List<PayrollUserRole>) repository.findAll();
	}
}