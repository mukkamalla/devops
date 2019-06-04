package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Company;
import com.valign.payroll.webservice.repository.CompanyRepository;
import com.valign.payroll.webservice.service.CompanyService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Company create(Company company) {

		return crudRepository.save(company);
	}

	@Override
	public Company delete(int id) {
		Company company = findById(id);
		if (company != null) {
			crudRepository.delete(company);
		}
		return company;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Company> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Company>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Company>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	
	@Override
	public Integer countRecord() {
		
		return (Integer) crudRepository.countRecords();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Company> findAllFilter( String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Company>) crudRepository.findBySearchTerm( filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Company findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Company update(Company company) {
		return crudRepository.save(company);
	}
	
	public java.util.List<Company> findAll(){
		return (java.util.List<Company>) crudRepository.findAll();
	}
}