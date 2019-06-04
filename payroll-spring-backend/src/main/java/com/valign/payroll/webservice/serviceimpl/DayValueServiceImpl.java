package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.DayValue;
import com.valign.payroll.webservice.repository.DayValueRepository;
import com.valign.payroll.webservice.service.DayValueService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DayValueServiceImpl implements DayValueService {

	@Autowired
	private DayValueRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public DayValue create(DayValue dayValue) {

		return crudRepository.save(dayValue);
	}

	@Override
	public DayValue delete(int id) {
		DayValue dayValue = findById(id);
		if (dayValue != null) {
			crudRepository.delete(dayValue);
		}
		return dayValue;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<DayValue> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<DayValue>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<DayValue>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public java.util.List<DayValue> findAllByLocaleId(int localeId) {
		return (java.util.List<DayValue>) crudRepository.findAllByLocaleId(localeId);

	}
	@Override
	public Integer countRecordByLocaleId(int localeId) {
		
		return (Integer) crudRepository.countRecordsByLocaleId(localeId);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<DayValue> findAllFilter(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<DayValue>) crudRepository.findBySearchTermByLocaleId(localeId, filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public DayValue findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public DayValue update(DayValue dayValue) {
		return crudRepository.save(dayValue);
	}
}