package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Timezone;
import com.valign.payroll.webservice.repository.TimezoneRepository;
import com.valign.payroll.webservice.service.TimezoneService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class TimezoneServiceImpl implements TimezoneService {

	@Autowired
	private TimezoneRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Timezone create(Timezone timezone) {

		return crudRepository.save(timezone);
	}

	@Override
	public Timezone delete(int id) {
		Timezone timezone = findById(id);
		if (timezone != null) {
			crudRepository.delete(timezone);
		}
		return timezone;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Timezone> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Timezone>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Timezone>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public java.util.List<Timezone> findAllByLocaleId(int localeId) {
		return (java.util.List<Timezone>) crudRepository.findAllByLocaleId(localeId);

	}
	@Override
	public Integer countRecordByLocaleId(int localeId) {
		
		return (Integer) crudRepository.countRecordsByLocaleId(localeId);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Timezone> findAllFilter(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Timezone>) crudRepository.findBySearchTermByLocaleId(localeId, filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Timezone findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Timezone update(Timezone timezone) {
		return crudRepository.save(timezone);
	}
}