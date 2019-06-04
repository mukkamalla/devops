package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Role;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

	void delete(Role role);


	@Query("SELECT COUNT(r) FROM Role r WHERE  r.recordStatus = 1   ")
	Integer countRecords();

	Role findById(int id);

	@Query("SELECT r FROM Role r WHERE ( r.recordStatus = 1 )  AND ( "
			+ " ( r.roleCode LIKE %?1%  ) OR " + " ( r.roleName LIKE '%'||?1||'%'  ) OR "  +  " ( r.id = ?1 ) ) ")
	Page<Role> findBySearchTerm(String searchTerm, Pageable pagable);

	Role save(Role role);
}