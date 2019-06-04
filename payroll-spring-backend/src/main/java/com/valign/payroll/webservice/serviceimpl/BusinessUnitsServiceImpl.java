package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.BusinessUnit;
import com.valign.payroll.webservice.repository.BusinessUnitsRepository;
import com.valign.payroll.webservice.service.BusinessUnitService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class BusinessUnitsServiceImpl implements BusinessUnitService {

	@Autowired
	private BusinessUnitsRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public BusinessUnit create(BusinessUnit businessUnits) {

		return crudRepository.save(businessUnits);
	}

	@Override
	public BusinessUnit delete(int id) {
		BusinessUnit businessUnits = findById(id);
		if (businessUnits != null) {
			crudRepository.delete(businessUnits);
		}
		return businessUnits;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<BusinessUnit> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<BusinessUnit>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<BusinessUnit>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	
	@Override
	public Integer countRecord() {
		
		return (Integer) crudRepository.countRecords();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<BusinessUnit> findAllFilter( String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<BusinessUnit>) crudRepository.findBySearchTerm( filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public BusinessUnit findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public BusinessUnit update(BusinessUnit businessUnits) {
		return crudRepository.save(businessUnits);
	}
	
	public java.util.List<BusinessUnit> findAll(){
		return (java.util.List<BusinessUnit>) crudRepository.findAll();
	}
}