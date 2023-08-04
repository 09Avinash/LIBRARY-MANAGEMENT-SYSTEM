package com.te.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.response.Successresponse;
import com.te.lms.service.PublisherService;

import lombok.RequiredArgsConstructor;

@RequestMapping(path = "/api/v1")
@RestController
@RequiredArgsConstructor
public class PublisherController {

	private final PublisherService publisherService;

	@RequestMapping(path = "/book-list")
	public ResponseEntity<Successresponse<List>> getBooks(@RequestParam("name") String name) {

	List list = publisherService.getBooks(name);

		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(Successresponse.<List>builder().data(list).message("book details are mentioned above").build());
	}

}
