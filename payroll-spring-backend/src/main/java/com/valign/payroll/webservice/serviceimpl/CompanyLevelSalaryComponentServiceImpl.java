package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.CompanyLevelSalaryComponent;
import com.valign.payroll.webservice.repository.CompanyLevelSalaryComponentRepository;
import com.valign.payroll.webservice.service.CompanyLevelSalaryComponentService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CompanyLevelSalaryComponentServiceImpl implements CompanyLevelSalaryComponentService {

	@Autowired
	private CompanyLevelSalaryComponentRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CompanyLevelSalaryComponent create(CompanyLevelSalaryComponent salaryComponent) {

		return crudRepository.save(salaryComponent);
	}

	@Override
	public CompanyLevelSalaryComponent delete(int id) {
		CompanyLevelSalaryComponent salaryComponent = findById(id);
		if (salaryComponent != null) {
			crudRepository.delete(salaryComponent);
		}
		return salaryComponent;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<CompanyLevelSalaryComponent> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<CompanyLevelSalaryComponent>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<CompanyLevelSalaryComponent>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	
	@Override
	public Integer countRecord() {
		
		return (Integer) crudRepository.countRecords();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<CompanyLevelSalaryComponent> findAllFilter( String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<CompanyLevelSalaryComponent>) crudRepository.findBySearchTerm( filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public CompanyLevelSalaryComponent findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public CompanyLevelSalaryComponent update(CompanyLevelSalaryComponent salaryComponent) {
		return crudRepository.save(salaryComponent);
	}
	
	public java.util.List<CompanyLevelSalaryComponent> findAll(){
		return (java.util.List<CompanyLevelSalaryComponent>) crudRepository.findAll();
	}
}