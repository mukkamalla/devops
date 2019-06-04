package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.RoleSalaryComponent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleSalaryComponentRepository extends PagingAndSortingRepository<RoleSalaryComponent, Integer> {

	void delete(RoleSalaryComponent roleSalaryComponent);


	@Query("SELECT COUNT(rsm) FROM RoleSalaryComponent rsm WHERE  rsm.recordStatus = 1   ")
	Integer countRecords();

	RoleSalaryComponent findById(int id);

	@Query("SELECT rsm FROM RoleSalaryComponent rsm WHERE ( rsm.recordStatus = 1 )  AND ( "
			  +  " ( rsm.roleSalaryMasterId = ?1 ) ) ")
	Page<RoleSalaryComponent> findBySearchTerm(String searchTerm, Pageable pagable);

	RoleSalaryComponent save(RoleSalaryComponent roleSalaryComponent);
}