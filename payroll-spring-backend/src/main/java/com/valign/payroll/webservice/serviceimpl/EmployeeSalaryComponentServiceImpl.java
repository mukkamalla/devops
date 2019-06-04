package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.EmployeeSalaryComponent;
import com.valign.payroll.webservice.repository.EmployeeSalaryComponentRepository;
import com.valign.payroll.webservice.service.EmployeeSalaryComponentService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EmployeeSalaryComponentServiceImpl implements EmployeeSalaryComponentService {

	@Autowired
	private EmployeeSalaryComponentRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EmployeeSalaryComponent create(EmployeeSalaryComponent employeeSalaryComponent) {

		return crudRepository.save(employeeSalaryComponent);
	}

	@Override
	public EmployeeSalaryComponent delete(int id) {
		EmployeeSalaryComponent employeeSalaryComponent = findById(id);
		if (employeeSalaryComponent != null) {
			crudRepository.delete(employeeSalaryComponent);
		}
		return employeeSalaryComponent;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<EmployeeSalaryComponent> findAllSortOnColumn(String pageSize, String pageNumber, String columnName,
			String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<EmployeeSalaryComponent>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<EmployeeSalaryComponent>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public Integer countRecord() {

		return (Integer) crudRepository.countRecords();

	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<EmployeeSalaryComponent> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<EmployeeSalaryComponent>) crudRepository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public EmployeeSalaryComponent findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public EmployeeSalaryComponent update(EmployeeSalaryComponent employeeSalaryComponent) {
		return crudRepository.save(employeeSalaryComponent);
	}

	public java.util.List<EmployeeSalaryComponent> findAll() {
		return (java.util.List<EmployeeSalaryComponent>) crudRepository.findAll();
	}
}