package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.LeaveType;
import com.valign.payroll.webservice.repository.LeaveTypeRepository;
import com.valign.payroll.webservice.service.LeaveTypeService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

	@Autowired
	private LeaveTypeRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public LeaveType create(LeaveType leaveType) {

		return crudRepository.save(leaveType);
	}

	@Override
	public LeaveType delete(int id) {
		LeaveType leaveType = findById(id);
		if (leaveType != null) {
			crudRepository.delete(leaveType);
		}
		return leaveType;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<LeaveType> findAllSortOnColumn(String pageSize, String pageNumber, String columnName,
			String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<LeaveType>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<LeaveType>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public Integer countRecord() {

		return (Integer) crudRepository.countRecords();

	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<LeaveType> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<LeaveType>) crudRepository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public LeaveType findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public LeaveType update(LeaveType leaveType) {
		return crudRepository.save(leaveType);
	}

	public java.util.List<LeaveType> findAll() {
		return (java.util.List<LeaveType>) crudRepository.findAll();
	}
}