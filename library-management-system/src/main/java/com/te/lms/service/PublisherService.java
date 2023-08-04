package com.te.lms.service;

import java.util.List;

import com.te.lms.dto.PublisherDTO;
import com.te.lms.entity.Book;
import com.te.lms.entity.Publisher;

public interface PublisherService {

	List<Book> getBooks(String name);

}
