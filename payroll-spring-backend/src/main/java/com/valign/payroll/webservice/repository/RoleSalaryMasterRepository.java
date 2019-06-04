package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.RoleSalaryMaster;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleSalaryMasterRepository extends PagingAndSortingRepository<RoleSalaryMaster, Integer> {

	void delete(RoleSalaryMaster roleSalaryMaster);


	@Query("SELECT COUNT(rsm) FROM RoleSalaryMaster rsm WHERE  rsm.recordStatus = 1   ")
	Integer countRecords();

	RoleSalaryMaster findById(int id);

	@Query("SELECT rsm FROM RoleSalaryMaster rsm WHERE ( rsm.recordStatus = 1 )  AND ( "
			+ " ( rsm.roleSalaryName LIKE %?1%  ) OR " + " ( rsm.roleSalaryName LIKE '%'||?1||'%'  ) OR "  +  " ( rsm.id = ?1 ) ) ")
	Page<RoleSalaryMaster> findBySearchTerm(String searchTerm, Pageable pagable);

	RoleSalaryMaster save(RoleSalaryMaster roleSalaryMaster);
}