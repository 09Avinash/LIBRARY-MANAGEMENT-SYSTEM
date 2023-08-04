package com.te.lms.dto;

import java.time.LocalDate;
import java.util.List;

import com.te.lms.entity.Book;
import com.te.lms.entity.Member;

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
public class LoanDTO {

	private LocalDate startDate;
	private LocalDate endDate;

	private List<Book> books;

	private List<Member> members;

}
