package com.te.lms.exception;

public class MemberAlreadyExistException extends RuntimeException {
	public MemberAlreadyExistException(String message) {
		super(message);
	}
}
