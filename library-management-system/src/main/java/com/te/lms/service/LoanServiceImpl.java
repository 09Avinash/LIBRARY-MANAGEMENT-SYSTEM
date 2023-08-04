package com.te.lms.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.te.lms.dto.FetchBookAuthorByFNnLNDTO;
import com.te.lms.entity.Book;
import com.te.lms.entity.Loan;
import com.te.lms.entity.Member;
import com.te.lms.repository.BookRepository;
import com.te.lms.repository.LoanRepository;
import com.te.lms.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {

	private final BookRepository bookrepository;
	private final MemberRepository memberRepository;
	private final LoanRepository loanRepository;

	@Override
	public String saveLoan(String title, String email) {

		Optional<Member> memberFromDB = memberRepository.findByEmail(email);
		Member member = memberFromDB.get();

		Optional<Book> bookFromDB = bookrepository.findByTitle(title);
		Book book = bookFromDB.get();

		Loan loan = Loan
				.builder()
				.loanId(UUID.randomUUID().toString())
				.startDate(LocalDate.now())
				.endDate(LocalDate.now().plusDays(15))
				.members(Arrays.asList(member))
				.books(Arrays.asList(book))
				.build();
		
		member.getLoans().add(loan);
		book.getLoans().add(loan);
		
		loanRepository.save(loan);
		
		return loan.getLoanId();
	}

	@Override
	public List<String> getLoanDetails() {
		
		List<Loan> loanListFromDB = loanRepository.findAll();
		return loanListFromDB.stream().map(l->"Member Name ->"+l.getMembers().get(0).getFirstName()
												+" Book Tiitle"+l.getBooks().get(0).getTitle())
										.collect(Collectors.toList());
	}

	@Override
	public List getBooksAuthor(FetchBookAuthorByFNnLNDTO bookAuthorByFNnLNDTO) {
		
		Member member = memberRepository.findByFirstNameAndLastName(bookAuthorByFNnLNDTO.getFirstName(),
				bookAuthorByFNnLNDTO.getLastName());
		
		List<Loan> loansFromDB = member.getLoans();
		
		
		
		return loansFromDB
				.stream()
				.map(l->l.getBooks().get(0).getTitle()+" "+l.getBooks().get(0).getAuthor().getFirstName())
				.collect(Collectors.toList());
	}

}
