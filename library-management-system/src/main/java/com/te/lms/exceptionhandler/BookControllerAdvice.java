package com.te.lms.exceptionhandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.lms.exception.BookTitleNotAvailableException;
import com.te.lms.exception.MemberAlreadyExistException;
import com.te.lms.exception.MemberDoesNotExistException;
import com.te.lms.response.ErrorResponse;

@RestControllerAdvice
public class BookControllerAdvice {

	@ExceptionHandler(value = { BookTitleNotAvailableException.class,
							MemberAlreadyExistException.class,
							MemberDoesNotExistException.class})
	public ErrorResponse handler(RuntimeException exe) {
		return ErrorResponse.builder()
				.error(exe.getMessage())
				.build();
	}

}
