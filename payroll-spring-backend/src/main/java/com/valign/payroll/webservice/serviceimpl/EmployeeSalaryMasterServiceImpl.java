package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.EmployeeSalaryMaster;
import com.valign.payroll.webservice.repository.EmployeeSalaryMasterRepository;
import com.valign.payroll.webservice.service.EmployeeSalaryMasterService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EmployeeSalaryMasterServiceImpl implements EmployeeSalaryMasterService {

	@Autowired
	private EmployeeSalaryMasterRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EmployeeSalaryMaster create(EmployeeSalaryMaster designation) {

		return crudRepository.save(designation);
	}

	@Override
	public EmployeeSalaryMaster delete(int id) {
		EmployeeSalaryMaster designation = findById(id);
		if (designation != null) {
			crudRepository.delete(designation);
		}
		return designation;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<EmployeeSalaryMaster> findAllSortOnColumn(String pageSize, String pageNumber, String columnName,
			String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<EmployeeSalaryMaster>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<EmployeeSalaryMaster>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public Integer countRecord() {

		return (Integer) crudRepository.countRecords();

	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<EmployeeSalaryMaster> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<EmployeeSalaryMaster>) crudRepository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public EmployeeSalaryMaster findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public EmployeeSalaryMaster update(EmployeeSalaryMaster designation) {
		return crudRepository.save(designation);
	}

	public java.util.List<EmployeeSalaryMaster> findAll() {
		return (java.util.List<EmployeeSalaryMaster>) crudRepository.findAll();
	}
}