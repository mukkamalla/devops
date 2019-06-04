package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Language;
import com.valign.payroll.webservice.repository.LanguageRepository;
import com.valign.payroll.webservice.service.LanguageService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Language create(Language language) {

		return crudRepository.save(language);
	}

	@Override
	public Language delete(int id) {
		Language country = findById(id);
		if (country != null) {
			crudRepository.delete(country);
		}
		return country;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Language> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Language>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Language>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	
	@Override
	public Integer countRecord() {
		
		return (Integer) crudRepository.countRecords();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Language> findAllFilter( String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Language>) crudRepository.findBySearchTerm( filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Language findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Language update(Language language) {
		return crudRepository.save(language);
	}
	
	public java.util.List<Language> findAll(){
		return (java.util.List<Language>) crudRepository.findAll();
	}
}
