package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.SalaryCycle;
import com.valign.payroll.webservice.repository.SalaryCycleRepository;
import com.valign.payroll.webservice.service.SalaryCycleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SalaryCycleServiceImpl implements SalaryCycleService {

	@Autowired
	private SalaryCycleRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public SalaryCycle create(SalaryCycle salaryCycle) {

		return crudRepository.save(salaryCycle);
	}

	@Override
	public SalaryCycle delete(int id) {
		SalaryCycle salaryCycle = findById(id);
		if (salaryCycle != null) {
			crudRepository.delete(salaryCycle);
		}
		return salaryCycle;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<SalaryCycle> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			
			return (Page<SalaryCycle>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<SalaryCycle>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	
	@Override
	public Integer countRecord() {
		
		return (Integer) crudRepository.countRecords();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<SalaryCycle> findAllFilter( String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<SalaryCycle>) crudRepository.findBySearchTerm( filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public SalaryCycle findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public SalaryCycle update(SalaryCycle salaryCycle) {
		return crudRepository.save(salaryCycle);
	}
	
	public java.util.List<SalaryCycle> findAll(){
		return (java.util.List<SalaryCycle>) crudRepository.findAll();
	}
}