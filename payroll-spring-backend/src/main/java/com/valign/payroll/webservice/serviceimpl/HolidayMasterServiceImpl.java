package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.HolidayMaster;
import com.valign.payroll.webservice.repository.HolidayMasterRepository;
import com.valign.payroll.webservice.service.HolidayMasterService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class HolidayMasterServiceImpl implements HolidayMasterService {

	@Autowired
	private HolidayMasterRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public HolidayMaster create(HolidayMaster holidayMaster) {

		return crudRepository.save(holidayMaster);
	}

	@Override
	public HolidayMaster delete(int id) {
		HolidayMaster holidayMaster = findById(id);
		if (holidayMaster != null) {
			crudRepository.delete(holidayMaster);
		}
		return holidayMaster;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<HolidayMaster> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<HolidayMaster>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<HolidayMaster>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	
	@Override
	public Integer countRecord() {
		
		return (Integer) crudRepository.countRecords();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<HolidayMaster> findAllFilter( String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<HolidayMaster>) crudRepository.findBySearchTerm( filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public HolidayMaster findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public HolidayMaster update(HolidayMaster holidayMaster) {
		return crudRepository.save(holidayMaster);
	}
	
	public java.util.List<HolidayMaster> findAll(){
		return (java.util.List<HolidayMaster>) crudRepository.findAll();
	}
}