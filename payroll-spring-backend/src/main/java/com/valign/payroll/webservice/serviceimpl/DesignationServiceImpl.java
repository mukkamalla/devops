package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Designation;
import com.valign.payroll.webservice.repository.DesignationRepository;
import com.valign.payroll.webservice.service.DesignationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	private DesignationRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Designation create(Designation designation) {

		return crudRepository.save(designation);
	}

	@Override
	public Designation delete(int id) {
		Designation designation = findById(id);
		if (designation != null) {
			crudRepository.delete(designation);
		}
		return designation;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Designation> findAllSortOnColumn(String pageSize, String pageNumber, String columnName,
			String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Designation>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Designation>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public Integer countRecord() {

		return (Integer) crudRepository.countRecords();

	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Designation> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Designation>) crudRepository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Designation findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Designation update(Designation designation) {
		return crudRepository.save(designation);
	}

	public java.util.List<Designation> findAll() {
		return (java.util.List<Designation>) crudRepository.findAll();
	}
}