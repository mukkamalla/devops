package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Shift;
import com.valign.payroll.webservice.repository.ShiftRepository;
import com.valign.payroll.webservice.service.ShiftService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ShiftServiceImpl implements ShiftService {

	@Autowired
	private ShiftRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Shift create(Shift shift) {

		return crudRepository.save(shift);
	}

	@Override
	public Shift delete(int id) {
		Shift shift = findById(id);
		if (shift != null) {
			crudRepository.delete(shift);
		}
		return shift;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Shift> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Shift>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Shift>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public Integer countRecord() {

		return (Integer) crudRepository.countRecords();

	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Shift> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Shift>) crudRepository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Shift findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Shift update(Shift shift) {
		return crudRepository.save(shift);
	}

	public java.util.List<Shift> findAll() {
		return (java.util.List<Shift>) crudRepository.findAll();
	}
}