package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.WeekValue;
import com.valign.payroll.webservice.repository.WeekValueRepository;
import com.valign.payroll.webservice.service.WeekValueService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class WeekValueServiceImpl implements WeekValueService {

	@Autowired
	private WeekValueRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public WeekValue create(WeekValue weekValue) {

		return crudRepository.save(weekValue);
	}

	@Override
	public WeekValue delete(int id) {
		WeekValue weekValue = findById(id);
		if (weekValue != null) {
			crudRepository.delete(weekValue);
		}
		return weekValue;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<WeekValue> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<WeekValue>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<WeekValue>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public java.util.List<WeekValue> findAllByLocaleId(int localeId) {
		return (java.util.List<WeekValue>) crudRepository.findAllByLocaleId(localeId);

	}
	@Override
	public Integer countRecordByLocaleId(int localeId) {
		
		return (Integer) crudRepository.countRecordsByLocaleId(localeId);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<WeekValue> findAllFilter(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<WeekValue>) crudRepository.findBySearchTermByLocaleId(localeId, filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public WeekValue findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public WeekValue update(WeekValue weekValue) {
		return crudRepository.save(weekValue);
	}
}