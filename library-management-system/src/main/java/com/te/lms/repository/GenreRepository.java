package com.te.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, String> {

	Genre findByName(String genreName);

}
