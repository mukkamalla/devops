package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.EmploymentStatus;
import com.valign.payroll.webservice.repository.EmploymentStatusRepository;
import com.valign.payroll.webservice.service.EmploymentStatusService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EmploymentStatusServiceImpl implements EmploymentStatusService {

	@Autowired
	private EmploymentStatusRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EmploymentStatus create(EmploymentStatus employmentStatus) {

		return crudRepository.save(employmentStatus);
	}

	@Override
	public EmploymentStatus delete(int id) {
		EmploymentStatus employmentStatus = findById(id);
		if (employmentStatus != null) {
			crudRepository.delete(employmentStatus);
		}
		return employmentStatus;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<EmploymentStatus> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			System.out.println("inside ascending ... findAll");
			return (Page<EmploymentStatus>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<EmploymentStatus>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public java.util.List<EmploymentStatus> findAllByLocaleId(int localeId) {
		return (java.util.List<EmploymentStatus>) crudRepository.findAllByLocaleId(localeId);

	}
	@Override
	public Integer countRecordByLocaleId(int localeId) {
		
		return (Integer) crudRepository.countRecordsByLocaleId(localeId);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<EmploymentStatus> findAllFilter(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<EmploymentStatus>) crudRepository.findBySearchTermByLocaleId(localeId, filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public EmploymentStatus findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public EmploymentStatus update(EmploymentStatus employmentStatus) {
		return crudRepository.save(employmentStatus);
	}
}