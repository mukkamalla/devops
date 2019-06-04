package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.CompanyGroup;
import com.valign.payroll.webservice.repository.CompanyGroupRepository;
import com.valign.payroll.webservice.service.CompanyGroupService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CompanyGroupServiceImpl implements CompanyGroupService {

	@Autowired
	private CompanyGroupRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CompanyGroup create(CompanyGroup companyGroup) {

		return crudRepository.save(companyGroup);
	}

	@Override
	public CompanyGroup delete(int id) {
		CompanyGroup companyGroup = findById(id);
		if (companyGroup != null) {
			crudRepository.delete(companyGroup);
		}
		return companyGroup;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<CompanyGroup> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<CompanyGroup>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<CompanyGroup>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	
	@Override
	public Integer countRecord() {
		
		return (Integer) crudRepository.countRecords();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<CompanyGroup> findAllFilter( String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<CompanyGroup>) crudRepository.findBySearchTerm( filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public CompanyGroup findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public CompanyGroup update(CompanyGroup companyGroup) {
		return crudRepository.save(companyGroup);
	}
	
	public java.util.List<CompanyGroup> findAll(){
		return (java.util.List<CompanyGroup>) crudRepository.findAll();
	}
}