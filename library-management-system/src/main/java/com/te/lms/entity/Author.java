package com.te.lms.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity

public class Author {

	@Id
	private String authorId = UUID.randomUUID().toString();
	@Column(unique = true)
	private String firstName;
	@Column(unique = true)
	private String lastName;
	@Column(unique = true)
	private LocalDate dateOfBirth;
	@Column(unique = true)
	private String country;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author")
	private List<Book> books;
}
