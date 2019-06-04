package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Department;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Integer> {

	void delete(Department department);


	@Query("SELECT COUNT(d) FROM Department d WHERE  d.recordStatus = 1   ")
	Integer countRecords();

	Department findById(int id);

	@Query("SELECT d FROM Department d WHERE ( d.recordStatus = 1 )  AND ( "
			+ " ( d.departmentName LIKE '%'||?1||'%'   ) OR "  + " ( d.departmentCode LIKE '%'||?1||'%'   ) OR "  +  " ( d.id = ?1 ) ) ")
	Page<Department> findBySearchTerm(String searchTerm, Pageable pagable);

	Department save(Department department);
}