package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Country;
import com.valign.payroll.webservice.repository.CountryRepository;
import com.valign.payroll.webservice.service.CountryService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Country create(Country country) {

		return crudRepository.save(country);
	}

	@Override
	public Country delete(int id) {
		Country country = findById(id);
		if (country != null) {
			crudRepository.delete(country);
		}
		return country;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Country> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Country>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Country>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public java.util.List<Country> findAllByLocaleId(int localeId) {
		return (java.util.List<Country>) crudRepository.findAllByLocaleId(localeId);

	}
	@Override
	public Integer countRecordByLocaleId(int localeId) {
		
		return (Integer) crudRepository.countRecordsByLocaleId(localeId);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Country> findAllFilter(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Country>) crudRepository.findBySearchTermByLocaleId(localeId, filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Country findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Country update(Country country) {
		return crudRepository.save(country);
	}
}