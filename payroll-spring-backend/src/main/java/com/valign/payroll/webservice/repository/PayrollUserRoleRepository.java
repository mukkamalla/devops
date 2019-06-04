package com.valign.payroll.webservice.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import com.valign.payroll.webservice.model.PayrollUserRole;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PayrollUserRoleRepository extends PagingAndSortingRepository<PayrollUserRole, Integer> {

	void delete(PayrollUserRole payrollUserRole);

	List<PayrollUserRole> findAll();


	@Query("SELECT COUNT(pur) FROM PayrollUserRole pur WHERE  pur.recordStatus = 1   ")
	Integer countRecords();
	
	PayrollUserRole findById(int id);

	@Query("SELECT pur FROM PayrollUserRole pur WHERE " + " pur.roleName LIKE '%'||?1||'%'  OR " + " pur.roleDesc LIKE '%'||?1||'%'  "
			)
	Page<PayrollUserRole> findBySearchTerm(String searchTerm, Pageable pagable);

	@Query("select u from PayrollUserRole u where u.roleName like '%'||?1||'%' order by roleName")
	List<PayrollUserRole> findByRoleNameContains(String filter);

	PayrollUserRole save(PayrollUserRole payrollUserRole);
}
