package com.te.lms.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {


	Optional<Member> findByFirstNameAndLastNameAndDateOfBirth(String firstName, String lastName, LocalDate dateOfBirth);

	Optional<Member> findByEmail(String email);

	Member findByFirstNameAndLastName(String firstName, String lastName);

}
