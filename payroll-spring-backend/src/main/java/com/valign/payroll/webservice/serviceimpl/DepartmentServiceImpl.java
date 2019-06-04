package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Department;
import com.valign.payroll.webservice.repository.DepartmentRepository;
import com.valign.payroll.webservice.service.DepartmentService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Department create(Department department) {

		return crudRepository.save(department);
	}

	@Override
	public Department delete(int id) {
		Department department = findById(id);
		if (department != null) {
			crudRepository.delete(department);
		}
		return department;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Department> findAllSortOnColumn(String pageSize, String pageNumber, String columnName,
			String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Department>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Department>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public Integer countRecord() {

		return (Integer) crudRepository.countRecords();

	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Department> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Department>) crudRepository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Department findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Department update(Department department) {
		return crudRepository.save(department);
	}

	public java.util.List<Department> findAll() {
		return (java.util.List<Department>) crudRepository.findAll();
	}
}