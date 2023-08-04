package com.te.lms.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
@Entity


public class Book {

	@Id
	private String bookId;
	@Column(unique = true)
	private String title;
	private LocalDate publicationYear;

	@JoinColumn(name = "author_id")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Author author;

	@JoinColumn(name = "publisher_id")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Publisher publisher;

	@JoinTable(name = "map_book_loan", 
			joinColumns = @JoinColumn(name = "book_id"), 
			inverseJoinColumns = @JoinColumn(name = "loan_id"))
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Loan> loans;

	@JoinTable(name = "map_book_genre", 
			joinColumns = @JoinColumn(name = "book_id"), 
			inverseJoinColumns = @JoinColumn(name = "genre_id"))
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Genre> genres;

}
