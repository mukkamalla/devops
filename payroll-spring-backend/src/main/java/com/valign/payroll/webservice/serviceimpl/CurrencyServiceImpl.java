package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Currency;
import com.valign.payroll.webservice.repository.CurrencyRepository;
import com.valign.payroll.webservice.service.CurrencyService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Currency create(Currency currency) {

		return crudRepository.save(currency);
	}

	@Override
	public Currency delete(int id) {
		Currency currency = findById(id);
		if (currency != null) {
			crudRepository.delete(currency);
		}
		return currency;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Currency> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Currency>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Currency>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public java.util.List<Currency> findAllByLocaleId(int localeId) {
		return (java.util.List<Currency>) crudRepository.findAllByLocaleId(localeId);

	}
	@Override
	public Integer countRecordByLocaleId(int localeId) {
		
		return (Integer) crudRepository.countRecordsByLocaleId(localeId);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Currency> findAllFilter(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Currency>) crudRepository.findBySearchTermByLocaleId(localeId, filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Currency findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Currency update(Currency currency) {
		return crudRepository.save(currency);
	}
}