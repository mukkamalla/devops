package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.RoleSalaryMaster;
import com.valign.payroll.webservice.repository.RoleSalaryMasterRepository;
import com.valign.payroll.webservice.service.RoleSalaryMasterService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class RoleSalaryMasterServiceImpl implements RoleSalaryMasterService {

	@Autowired
	private RoleSalaryMasterRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public RoleSalaryMaster create(RoleSalaryMaster roleSalaryMaster) {

		return crudRepository.save(roleSalaryMaster);
	}

	@Override
	public RoleSalaryMaster delete(int id) {
		RoleSalaryMaster roleSalaryMaster = findById(id);
		if (roleSalaryMaster != null) {
			crudRepository.delete(roleSalaryMaster);
		}
		return roleSalaryMaster;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<RoleSalaryMaster> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<RoleSalaryMaster>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<RoleSalaryMaster>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	
	@Override
	public Integer countRecord() {
		
		return (Integer) crudRepository.countRecords();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<RoleSalaryMaster> findAllFilter( String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<RoleSalaryMaster>) crudRepository.findBySearchTerm( filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public RoleSalaryMaster findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public RoleSalaryMaster update(RoleSalaryMaster roleSalaryMaster) {
		return crudRepository.save(roleSalaryMaster);
	}
	
	public java.util.List<RoleSalaryMaster> findAll(){
		return (java.util.List<RoleSalaryMaster>) crudRepository.findAll();
	}
}