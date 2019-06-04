package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Locale;
import com.valign.payroll.webservice.repository.LocaleRepository;
import com.valign.payroll.webservice.service.LocaleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class LocaleServiceImpl implements LocaleService {

	@Autowired
	private LocaleRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Locale create(Locale locale) {

		return crudRepository.save(locale);
	}

	@Override
	public Locale delete(int id) {
		Locale locale = findById(id);
		if (locale != null) {
			crudRepository.delete(locale);
		}
		return locale;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Locale> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Locale>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Locale>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	
	@Override
	public Integer countRecord() {
		
		return (Integer) crudRepository.countRecords();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Locale> findAllFilter( String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Locale>) crudRepository.findBySearchTerm( filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Locale findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Locale update(Locale locale) {
		return crudRepository.save(locale);
	}
	
	public java.util.List<Locale> findAll(){
		return (java.util.List<Locale>) crudRepository.findAll();
	}
}