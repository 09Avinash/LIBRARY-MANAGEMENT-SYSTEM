package com.te.lms.service;

import java.util.List;

import com.te.lms.dto.BookDTO;

public interface BookService {

	String savebook(BookDTO bookDTO);

	BookDTO getBookDetails(String title);

	List getAllBookDetails();

	List getBooksBasedOnGenre(String genreName);

	List getCountOfBookBorrowed();


}
