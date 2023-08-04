package com.te.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.FetchBookAuthorByFNnLNDTO;
import com.te.lms.response.Successresponse;
import com.te.lms.service.LoanService;

import lombok.RequiredArgsConstructor;

@RequestMapping(path = "/api/v1")
@RestController
@RequiredArgsConstructor
public class LoanController {

	private final LoanService loanService;

	@PostMapping(path = "/loan")
	public ResponseEntity<Successresponse<String>> saveLoan(@RequestParam("title") String title,
															@RequestParam("email") String email) {

		String loanId = loanService.saveLoan(title, email);

		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(Successresponse.<String>builder().data(loanId).message("loan saved").build());

	}
	
	@GetMapping(path = "/loan-list")
	public ResponseEntity<Successresponse<List>> loanMembersList(){
		
		List listOfMembers = loanService.getLoanDetails();
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(Successresponse.<List>builder().data(listOfMembers).message("loan saved").build());
	}
	
	@GetMapping(path = "/member-loanhistory")
	public ResponseEntity<Successresponse<List>> getBooksAuthor(@RequestBody FetchBookAuthorByFNnLNDTO bookAuthorByFNnLNDTO){
		
		List listOfAuthors = loanService.getBooksAuthor(bookAuthorByFNnLNDTO);
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(Successresponse.<List>builder().data(listOfAuthors).message("list of author").build());
	}

}
