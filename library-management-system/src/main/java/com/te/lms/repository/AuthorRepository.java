package com.te.lms.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, String> {

	Optional<Author> findByFirstNameAndLastNameAndDateOfBirthAndCountry(String firstName, String lastName,
			LocalDate dateOfBirth, String country);

}
