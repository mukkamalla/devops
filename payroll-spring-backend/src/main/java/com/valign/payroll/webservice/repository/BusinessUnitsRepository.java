package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.BusinessUnit;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BusinessUnitsRepository extends PagingAndSortingRepository<BusinessUnit, Integer> {

	void delete(BusinessUnit businessUnits);


	@Query("SELECT COUNT(bu) FROM BusinessUnit bu WHERE  bu.recordStatus = 1   ")
	Integer countRecords();

	BusinessUnit findById(int id);

	@Query("SELECT bu FROM BusinessUnit bu WHERE ( bu.recordStatus = 1 )  AND ( "
			+ " ( bu.businessUnitCode LIKE '%'||?1||'%'   ) OR " + " ( bu.businessUnitName LIKE '%'||?1||'%'   ) OR "  +  " ( bu.id = ?1 ) ) ")
	Page<BusinessUnit> findBySearchTerm(String searchTerm, Pageable pagable);

	BusinessUnit save(BusinessUnit businessUnits);
}