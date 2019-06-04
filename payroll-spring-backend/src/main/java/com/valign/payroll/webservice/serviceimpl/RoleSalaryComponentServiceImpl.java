package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.RoleSalaryComponent;
import com.valign.payroll.webservice.repository.RoleSalaryComponentRepository;
import com.valign.payroll.webservice.service.RoleSalaryComponentService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class RoleSalaryComponentServiceImpl implements RoleSalaryComponentService {

	@Autowired
	private RoleSalaryComponentRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public RoleSalaryComponent create(RoleSalaryComponent roleSalaryComponent) {

		return crudRepository.save(roleSalaryComponent);
	}

	@Override
	public RoleSalaryComponent delete(int id) {
		RoleSalaryComponent roleSalaryComponent = findById(id);
		if (roleSalaryComponent != null) {
			crudRepository.delete(roleSalaryComponent);
		}
		return roleSalaryComponent;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<RoleSalaryComponent> findAllSortOnColumn(String pageSize, String pageNumber, String columnName,
			String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<RoleSalaryComponent>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<RoleSalaryComponent>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public Integer countRecord() {

		return (Integer) crudRepository.countRecords();

	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<RoleSalaryComponent> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<RoleSalaryComponent>) crudRepository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public RoleSalaryComponent findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public RoleSalaryComponent update(RoleSalaryComponent roleSalaryComponent) {
		return crudRepository.save(roleSalaryComponent);
	}

	public java.util.List<RoleSalaryComponent> findAll() {
		return (java.util.List<RoleSalaryComponent>) crudRepository.findAll();
	}
}