package com.te.lms.utils;

import java.util.UUID;
import java.util.stream.Collectors;

import com.te.lms.dto.AuthorDTO;
import com.te.lms.dto.BookDTO;
import com.te.lms.dto.PublisherDTO;
import com.te.lms.entity.Author;
import com.te.lms.entity.Book;
import com.te.lms.entity.Genre;
import com.te.lms.entity.Publisher;

public class BookUtil {

	public static Book convertBookDTOtoBook(BookDTO bookDTO) {
		
		Book book = Book.builder()
				.bookId(UUID.randomUUID().toString())
				.title(bookDTO.getTitle())
				.publicationYear(bookDTO.getPublicationYear())
				.author(Author.
						builder().
						firstName(bookDTO.getAuthor().getFirstName())
						.lastName(bookDTO.getAuthor().getLastName())
						.dateOfBirth(bookDTO.getAuthor().getDateOfBirth())
						.country(bookDTO.getAuthor().getCountry())
						.build())
				.publisher(Publisher
						.builder()
						.name(bookDTO.getPublisher().getName())
						.location(bookDTO.getPublisher().getLocation())
						.build())
				.genres(bookDTO.
						getGenres()
						.stream()
						.map(genreName->Genre
								.builder()
								.name(genreName)
								.genreId(UUID.randomUUID().toString())
								.build())
						.collect(Collectors.toList())
						)
				.build();
		
		return book;
	}
	
	public static BookDTO BookToBookDTO(Book bookFromDb) {
		
		return BookDTO.builder().title(bookFromDb.getTitle())
				.publicationYear(bookFromDb.getPublicationYear())
				.author(AuthorDTO
						.builder()
						.firstName(bookFromDb.getAuthor().getFirstName())
						.lastName(bookFromDb.getAuthor().getLastName())
						.dateOfBirth(bookFromDb.getAuthor().getDateOfBirth())
						.country(bookFromDb.getAuthor().getCountry())
						.build())
				.publisher(PublisherDTO
						.builder()
						.name(bookFromDb.getPublisher().getName())
						.location(bookFromDb.getPublisher().getLocation())
						.build())
				.genres(bookFromDb
						.getGenres()
						.stream()
						.map(g->g.getName())
						.collect(Collectors.toList()))
				.build();
		
	}

}
