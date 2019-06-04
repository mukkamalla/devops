package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Salutation;
import com.valign.payroll.webservice.repository.SalutationRepository;
import com.valign.payroll.webservice.service.SalutationService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SalutationServiceImpl implements SalutationService {

	
	@Autowired
	private SalutationRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Salutation create(Salutation salutation) {

		return crudRepository.save(salutation);
	}

	@Override
	public Salutation delete(int id) {
		Salutation salutation = findById(id);
		if (salutation != null) {
			crudRepository.delete(salutation);
		}
		return salutation;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Salutation> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Salutation>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Salutation>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public java.util.List<Salutation> findAllByLocaleId(int localeId) {
		return (java.util.List<Salutation>) crudRepository.findAllByLocaleId(localeId);

	}
	@Override
	public Integer countRecordByLocaleId(int localeId) {
		
		return (Integer) crudRepository.countRecordsByLocaleId(localeId);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Salutation> findAllFilter(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Salutation>) crudRepository.findBySearchTermByLocaleId(localeId, filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Salutation findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Salutation update(Salutation salutation) {
		return crudRepository.save(salutation);
	}
}