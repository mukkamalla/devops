package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.TransactionCurrency;
import com.valign.payroll.webservice.repository.TransactionCurrencyRepository;
import com.valign.payroll.webservice.service.TransactionCurrencyService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class TransactionCurrencyServiceImpl implements TransactionCurrencyService {

	@Autowired
	private TransactionCurrencyRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TransactionCurrency create(TransactionCurrency transactionCurrency) {

		return crudRepository.save(transactionCurrency);
	}

	@Override
	public TransactionCurrency delete(int id) {
		TransactionCurrency transactionCurrency = findById(id);
		if (transactionCurrency != null) {
			crudRepository.delete(transactionCurrency);
		}
		return transactionCurrency;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<TransactionCurrency> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<TransactionCurrency>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<TransactionCurrency>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public Integer countRecord() {

		return (Integer) crudRepository.countRecords();

	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<TransactionCurrency> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<TransactionCurrency>) crudRepository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public TransactionCurrency findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public TransactionCurrency update(TransactionCurrency transactionCurrency) {
		return crudRepository.save(transactionCurrency);
	}

	public java.util.List<TransactionCurrency> findAll() {
		return (java.util.List<TransactionCurrency>) crudRepository.findAll();
	}
}