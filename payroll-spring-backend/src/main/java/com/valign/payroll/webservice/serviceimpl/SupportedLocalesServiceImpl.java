package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.SupportedLocales;
import com.valign.payroll.webservice.repository.SupportedLocalesRepository;
import com.valign.payroll.webservice.service.SupportedLocalesService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SupportedLocalesServiceImpl implements SupportedLocalesService {

	@Autowired
	private SupportedLocalesRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public SupportedLocales create(SupportedLocales supportedLocales) {

		return crudRepository.save(supportedLocales);
	}

	@Override
	public SupportedLocales delete(int id) {
		SupportedLocales supportedLocales = findById(id);
		if (supportedLocales != null) {
			crudRepository.delete(supportedLocales);
		}
		return supportedLocales;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<SupportedLocales> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<SupportedLocales>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<SupportedLocales>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public Integer countRecord() {

		return (Integer) crudRepository.countRecords();

	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<SupportedLocales> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<SupportedLocales>) crudRepository.findBySearchTerm(filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public SupportedLocales findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public SupportedLocales update(SupportedLocales supportedLocales) {
		return crudRepository.save(supportedLocales);
	}

	public java.util.List<SupportedLocales> findAll() {
		return (java.util.List<SupportedLocales>) crudRepository.findAll();
	}
}