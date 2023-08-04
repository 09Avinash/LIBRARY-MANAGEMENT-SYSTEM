package com.te.lms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.te.lms.dto.BookDTO;
import com.te.lms.entity.Book;
import com.te.lms.entity.Publisher;
import com.te.lms.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PublisherServiceImpl implements PublisherService {

	private final PublisherRepository publisherRepository;
	//private final BookRepository bookRepository;

	@Override
	public List<Book> getBooks(String name) {
		Publisher publisherFromDB = publisherRepository.findByName(name);
		List<Book> books=publisherFromDB.getBooks();
		List listOfBooks=books
		.stream()
		.map(b->b.getTitle().concat(" Author Name"+b.getAuthor().getFirstName()))
		.collect(Collectors.toList());
		return listOfBooks;
	}

}
