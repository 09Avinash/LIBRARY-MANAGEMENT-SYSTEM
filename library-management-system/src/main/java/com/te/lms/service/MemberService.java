package com.te.lms.service;

import com.te.lms.dto.MemberDTO;

public interface MemberService {

	String saveMember(MemberDTO memberDTO);

	MemberDTO getMember(String email);

	int getAverageMemberLoanList();
	

}
