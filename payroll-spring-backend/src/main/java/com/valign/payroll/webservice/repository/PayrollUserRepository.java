package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import com.valign.payroll.webservice.model.PayrollUser;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PayrollUserRepository extends PagingAndSortingRepository<PayrollUser, Integer> {

	void delete(PayrollUser user);

	List<PayrollUser> findAll();

	PayrollUser findById(int id);

	@Query("SELECT pu FROM PayrollUser pu WHERE " + " pu.firstName LIKE '%'||?1||'%'  OR " + " pu.lastName LIKE '%'||?1||'%'  OR "
			+ " pu.emailId LIKE '%'||?1||'%'  " )
	Page<PayrollUser> findBySearchTerm(String searchTerm, Pageable pagable);

	@Query("select u from PayrollUser u where u.firstName like '%'||?1||'%' order by firstName")
	List<PayrollUser> findByFirstNameContains(String filter);
	
	@Query("select u from PayrollUser u where u.userId like '%'||?1||'%' ")
	List<PayrollUser> findByUserIdContains(String filter);

	PayrollUser save(PayrollUser user);
}
