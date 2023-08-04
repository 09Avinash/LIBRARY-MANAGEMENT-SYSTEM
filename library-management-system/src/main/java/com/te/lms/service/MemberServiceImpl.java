package com.te.lms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.te.lms.constants.LibraryConstant;
import com.te.lms.dto.MemberDTO;
import com.te.lms.entity.Loan;
import com.te.lms.entity.Member;
import com.te.lms.exception.MemberAlreadyExistException;
import com.te.lms.exception.MemberDoesNotExistException;
import com.te.lms.repository.LoanRepository;
import com.te.lms.repository.MemberRepository;
import com.te.lms.utils.MemberUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final LoanRepository loanRepository;

	@Override
	public String saveMember(MemberDTO memberDTO) {
		Optional<Member> memberFromDB = memberRepository.findByFirstNameAndLastNameAndDateOfBirth(
				memberDTO.getFirstName(), memberDTO.getLastName(), memberDTO.getDateOfBirth());

		if (!memberFromDB.isPresent()) {

			Member member = MemberUtils.memberDTOtoMember(memberDTO);

			memberRepository.save(member);

			return member.getMemberId();
		}

		throw new MemberAlreadyExistException(LibraryConstant.MEMBER_ALREADY_EXIST);
	}

	@Override
	public MemberDTO getMember(String email) {

		Optional<Member> memberFromDB = memberRepository.findByEmail(email);
		if (memberFromDB.isPresent()) {
			Member member = memberFromDB.get();
			MemberDTO memberDTO = MemberUtils.memberToMembertDTO(member);
			return memberDTO;
		}
		throw new MemberDoesNotExistException(LibraryConstant.MEMBER_DOESNOT_EXIST);
	}

	@Override
	public int getAverageMemberLoanList() {
		List<Member> listOfMembersFromDB = memberRepository.findAll();
		List list = listOfMembersFromDB.stream().map(b -> b.getFirstName() + " " + b.getLoans().size())
				.collect(Collectors.toList());
		int totalNumberOfMembers = listOfMembersFromDB.size();
		List<Loan> loanFromRepositoryDB = loanRepository.findAll();
		int totalNumberOfBooksBorrowed = loanFromRepositoryDB.get(0).getBooks().size();

		int average = totalNumberOfBooksBorrowed / totalNumberOfMembers;

		return average;
	}

}
