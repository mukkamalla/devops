package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Location;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LocationRepository extends PagingAndSortingRepository<Location, Integer> {

	void delete(Location location);


	@Query("SELECT COUNT(l) FROM Location l WHERE  l.recordStatus = 1   ")
	Integer countRecords();

	Location findById(int id);

	@Query("SELECT l FROM Location l WHERE ( l.recordStatus = 1 )  AND ( "
			+ " ( l.locationName LIKE '%'||?1||'%'  ) OR " + " ( l.city LIKE '%'||?1||'%'  ) OR "
			+ " ( l.address1 LIKE '%'||?1||'%'  ) OR "
			+ " ( l.address2 LIKE '%'||?1||'%'  ) OR "
			+  " ( l.id = ?1 ) ) ")
	Page<Location> findBySearchTerm(String searchTerm, Pageable pagable);

	Location save(Location location);
}