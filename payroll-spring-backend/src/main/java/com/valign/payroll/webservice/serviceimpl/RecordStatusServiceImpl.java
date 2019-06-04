package com.valign.payroll.webservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.valign.payroll.webservice.model.RecordStatus;
import com.valign.payroll.webservice.repository.RecordStatusRepository;
import com.valign.payroll.webservice.service.RecordStatusService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class RecordStatusServiceImpl implements RecordStatusService {

	@Autowired
	private RecordStatusRepository crudRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public RecordStatus create(RecordStatus recordStatus) {

		return crudRepository.save(recordStatus);
	}

	@Override
	public RecordStatus delete(int id) {
		RecordStatus recordStatus = findById(id);
		if (recordStatus != null) {
			crudRepository.delete(recordStatus);
		}
		return recordStatus;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<RecordStatus> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc) {
		if (asc_desc.equalsIgnoreCase("ASC")) {
			
			return (Page<RecordStatus>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.ASC, columnName));
		} else {

			return (Page<RecordStatus>) crudRepository.findAll(new PageRequest(Integer.parseInt(pageNumber),
					Integer.parseInt(pageSize), Sort.Direction.DESC, columnName));
		}
	}

	@Override
	public java.util.List<RecordStatus> findAllByLocaleId(int localeId) {
		return (java.util.List<RecordStatus>) crudRepository.findAllByLocaleId(localeId);

	}
	@Override
	public Integer countRecordByLocaleId(int localeId) {
		
		return (Integer) crudRepository.countRecordsByLocaleId(localeId);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<RecordStatus> findAllFilter(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter) {
		return (Page<RecordStatus>) crudRepository.findBySearchTermByLocaleId(localeId, filter,
				new PageRequest(Integer.parseInt(pageNumber), Integer.parseInt(pageSize)));

	}

	@Override
	public RecordStatus findById(int id) {
		return crudRepository.findById(id);
	}

	@Override
	public RecordStatus update(RecordStatus recordStatus) {
		return crudRepository.save(recordStatus);
	}
}