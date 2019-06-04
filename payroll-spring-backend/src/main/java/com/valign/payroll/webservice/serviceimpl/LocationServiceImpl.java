package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Location;
import com.valign.payroll.webservice.repository.LocationRepository;
import com.valign.payroll.webservice.service.LocationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Location create(Location location) {

		return crudRepository.save(location);
	}

	@Override
	public Location delete(int id) {
		Location location = findById(id);
		if (location != null) {
			crudRepository.delete(location);
		}
		return location;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Location> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Location>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Location>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public Integer countRecord() {

		return (Integer) crudRepository.countRecords();

	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Location> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Location>) crudRepository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Location findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Location update(Location location) {
		return crudRepository.save(location);
	}

	public java.util.List<Location> findAll() {
		return (java.util.List<Location>) crudRepository.findAll();
	}
}