package com.te.lms.service;

import java.util.List;

import com.te.lms.dto.FetchBookAuthorByFNnLNDTO;

public interface LoanService {

	String saveLoan(String title, String email);

	List getLoanDetails();

	List getBooksAuthor(FetchBookAuthorByFNnLNDTO bookAuthorByFNnLNDTO);

	

}
