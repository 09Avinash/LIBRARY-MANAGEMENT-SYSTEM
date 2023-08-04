package com.te.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, String> {

	Loan findByLoanId(String loanId);	

}
