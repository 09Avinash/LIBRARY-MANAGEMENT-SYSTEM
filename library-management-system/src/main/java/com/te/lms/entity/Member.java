package com.te.lms.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;

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
@Entity
public class Member {

	@Id
	private String memberId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;

	@Column(unique = true)
	private String email;
	
	@JoinTable(name = "map_member_loan",
			joinColumns = @JoinColumn(name = "member_id"), 
			inverseJoinColumns = @JoinColumn(name = "loan_id"))
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Loan> loans;

}
