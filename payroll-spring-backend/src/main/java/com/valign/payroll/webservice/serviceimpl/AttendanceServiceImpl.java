package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Attendance;
import com.valign.payroll.webservice.repository.AttendanceRepository;
import com.valign.payroll.webservice.service.AttendanceService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Attendance create(Attendance attendance) {

		return crudRepository.save(attendance);
	}

	@Override
	public Attendance delete(int id) {
		Attendance attendance = findById(id);
		if (attendance != null) {
			crudRepository.delete(attendance);
		}
		return attendance;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Attendance> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			
			return (Page<Attendance>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Attendance>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	
	@Override
	public Integer countRecord() {
		
		return (Integer) crudRepository.countRecords();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Attendance> findAllFilter( String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Attendance>) crudRepository.findBySearchTerm( filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Attendance findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Attendance update(Attendance attendance) {
		return crudRepository.save(attendance);
	}
	
	public java.util.List<Attendance> findAll(){
		return (java.util.List<Attendance>) crudRepository.findAll();
	}
}