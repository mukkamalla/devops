package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Employee;
import com.valign.payroll.webservice.repository.EmployeeRepository;
import com.valign.payroll.webservice.service.EmployeeService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Employee create(Employee role) {

		return crudRepository.save(role);
	}

	@Override
	public Employee delete(int id) {
		Employee role = findById(id);
		if (role != null) {
			crudRepository.delete(role);
		}
		return role;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Employee> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Employee>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Employee>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public Integer countRecord() {

		return (Integer) crudRepository.countRecords();

	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Employee> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Employee>) crudRepository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Employee findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Employee update(Employee role) {
		return crudRepository.save(role);
	}

	public java.util.List<Employee> findAll() {
		return (java.util.List<Employee>) crudRepository.findAll();
	}
}