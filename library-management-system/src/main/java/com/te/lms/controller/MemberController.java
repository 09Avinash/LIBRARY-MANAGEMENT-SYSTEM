package com.te.lms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.constants.LibraryConstant;
import com.te.lms.dto.MemberDTO;
import com.te.lms.response.Successresponse;
import com.te.lms.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1")
public class MemberController {

	private final MemberService memberService;

	@GetMapping(path = "/member")
	public MemberDTO jsonPattern(MemberDTO memberDTO) {

		return MemberDTO.builder().build();

	}

	@PostMapping(path = "/member")
	public ResponseEntity<Successresponse<String>> saveMember(@RequestBody MemberDTO memberDTO) {

		String memberId = memberService.saveMember(memberDTO);

		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(Successresponse.<String>builder().data(memberId).message(LibraryConstant.MEMBER_CREATED).build());
	}

	@GetMapping(path = "/member-details")
	public ResponseEntity<Successresponse<MemberDTO>> getMemberDetails(
												@RequestParam("email") String email) {

		MemberDTO memberDTO = memberService.getMember(email);

		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(Successresponse.<MemberDTO>builder().data(memberDTO).message(LibraryConstant.MEMBER_FOUND).build());
	}

	@GetMapping(path = "/member-average-bookcount")
	public ResponseEntity<Successresponse<Integer>> getAverageMemberBorrowList(){
	
		int average=memberService.getAverageMemberLoanList();
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(Successresponse.<Integer>builder()
						.data(average).message("Average Books borrowed by each member")
						.build()
					 );
		
	}
}
