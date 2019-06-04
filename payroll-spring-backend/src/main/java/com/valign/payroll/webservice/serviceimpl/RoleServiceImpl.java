package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Role;
import com.valign.payroll.webservice.repository.RoleRepository;
import com.valign.payroll.webservice.service.RoleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Role create(Role role) {

		return crudRepository.save(role);
	}

	@Override
	public Role delete(int id) {
		Role role = findById(id);
		if (role != null) {
			crudRepository.delete(role);
		}
		return role;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Role> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Role>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Role>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	
	@Override
	public Integer countRecord() {
		
		return (Integer) crudRepository.countRecords();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Role> findAllFilter( String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Role>) crudRepository.findBySearchTerm( filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Role findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Role update(Role role) {
		return crudRepository.save(role);
	}
	
	public java.util.List<Role> findAll(){
		return (java.util.List<Role>) crudRepository.findAll();
	}
}
