package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Grade;
import com.valign.payroll.webservice.repository.GradeRepository;
import com.valign.payroll.webservice.service.GradeService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Grade create(Grade grade) {

		return crudRepository.save(grade);
	}

	@Override
	public Grade delete(int id) {
		Grade grade = findById(id);
		if (grade != null) {
			crudRepository.delete(grade);
		}
		return grade;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Grade> findAllSortOnColumn(String pageSize, String pageNumber, String columnName,
			String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Grade>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Grade>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public Integer countRecord() {

		return (Integer) crudRepository.countRecords();

	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Grade> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Grade>) crudRepository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Grade findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Grade update(Grade grade) {
		return crudRepository.save(grade);
	}

	public java.util.List<Grade> findAll() {
		return (java.util.List<Grade>) crudRepository.findAll();
	}
}