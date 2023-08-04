package com.te.lms.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import com.te.lms.entity.Author;
import com.te.lms.entity.Book;
import com.te.lms.entity.Genre;
import com.te.lms.entity.Loan;
import com.te.lms.entity.Publisher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class BookDTO {

	private String title;
	private LocalDate publicationYear;
	private AuthorDTO author;
	private PublisherDTO publisher;
	private List<String> genres;

}
