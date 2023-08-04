package com.te.lms.utils;

import java.util.UUID;

import com.te.lms.dto.MemberDTO;
import com.te.lms.entity.Member;

public class MemberUtils {
	
	public static Member memberDTOtoMember(MemberDTO memberDTO) {
		return Member.builder()
				.memberId(UUID.randomUUID().toString())
				.firstName(memberDTO.getFirstName())
				.lastName(memberDTO.getLastName())
				.dateOfBirth(memberDTO.getDateOfBirth())
				.email(memberDTO.getEmail())
				.build();
	}

	public static MemberDTO memberToMembertDTO(Member member) {
		return MemberDTO
				.builder()
				.firstName(member.getFirstName())
				.lastName(member.getLastName())
				.dateOfBirth(member.getDateOfBirth())
				.email(member.getEmail())
				.build();
		
	}

}
