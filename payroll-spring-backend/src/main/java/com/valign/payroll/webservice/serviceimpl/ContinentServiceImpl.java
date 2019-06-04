package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Continent;
import com.valign.payroll.webservice.repository.ContinentRepository;
import com.valign.payroll.webservice.service.ContinentService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ContinentServiceImpl implements ContinentService {

	@Autowired
	private ContinentRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Continent create(Continent continent) {
		Continent continent_new = new Continent();
		continent_new.setContinentCode(continent.getContinentCode());
		continent_new.setContinentName(continent.getContinentName());
		continent_new.setCreationBy(1);
		continent_new.setCreationDate(new java.util.Date());
		continent_new.setLocaleId(1);
		continent_new.setRecordStatus(1);
		continent_new.setUpdatedBy(1);
		continent_new.setUpdatedDate(new java.util.Date());

		return crudRepository.save(continent_new);
	}

	@Override
	public Continent delete(int id) {
		Continent continent = findById(id);
		if (continent != null) {
			crudRepository.delete(continent);
		}
		return continent;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Continent> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Continent>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Continent>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public java.util.List<Continent> findAllByLocaleId(int localeId) {
		return (java.util.List<Continent>) crudRepository.findAllByLocaleId(localeId);

	}
	@Override
	public Integer countRecordByLocaleId(int localeId) {
		
		return (Integer) crudRepository.countRecordsByLocaleId(localeId);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Continent> findAllFilter(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Continent>) crudRepository.findBySearchTermByLocaleId(localeId, filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Continent findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Continent update(Continent continent) {
		
		Continent continent_new = new Continent();
		continent_new.setContinentCode(continent.getContinentCode());
		continent_new.setContinentName(continent.getContinentName());
		continent_new.setUpdatedBy(1);
		continent_new.setUpdatedDate(new java.util.Date());
		
		
		
		return crudRepository.save(continent_new);
	}
}