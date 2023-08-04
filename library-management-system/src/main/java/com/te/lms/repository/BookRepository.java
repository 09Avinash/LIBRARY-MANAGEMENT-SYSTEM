package com.te.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {

	Optional<Book> findByTitle(String title);


}
