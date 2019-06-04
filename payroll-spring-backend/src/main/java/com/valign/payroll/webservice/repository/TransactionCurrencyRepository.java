package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.TransactionCurrency;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionCurrencyRepository extends PagingAndSortingRepository<TransactionCurrency, Integer> {

	void delete(TransactionCurrency transactionCurrency);


	@Query("SELECT COUNT(tc) FROM TransactionCurrency tc WHERE  tc.recordStatus = 1   ")
	Integer countRecords();

	TransactionCurrency findById(int id);

	@Query("SELECT tc FROM TransactionCurrency tc WHERE ( tc.recordStatus = 1 )  AND ( "
			   +  " ( tc.id = ?1 ) ) ")
	Page<TransactionCurrency> findBySearchTerm(String searchTerm, Pageable pagable);

	TransactionCurrency save(TransactionCurrency transactionCurrency);
}