package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.Gender;
import com.valign.payroll.webservice.repository.GenderRepository;
import com.valign.payroll.webservice.service.GenderService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class GenderServiceImpl implements GenderService {

	@Autowired
	private GenderRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Gender create(Gender gender) {

		return crudRepository.save(gender);
	}

	@Override
	public Gender delete(int id) {
		Gender gender = findById(id);
		if (gender != null) {
			crudRepository.delete(gender);
		}
		return gender;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Gender> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<Gender>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<Gender>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public java.util.List<Gender> findAllByLocaleId(int localeId) {
		return (java.util.List<Gender>) crudRepository.findAllByLocaleId(localeId);

	}
	@Override
	public Integer countRecordByLocaleId(int localeId) {
		
		return (Integer) crudRepository.countRecordsByLocaleId(localeId);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Gender> findAllFilter(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<Gender>) crudRepository.findBySearchTermByLocaleId(localeId, filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public Gender findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public Gender update(Gender gender) {
		return crudRepository.save(gender);
	}
}