package com.te.lms.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.constants.LibraryConstant;
import com.te.lms.dto.AuthorDTO;
import com.te.lms.dto.BookDTO;
import com.te.lms.dto.PublisherDTO;
import com.te.lms.entity.Book;
import com.te.lms.response.Successresponse;
import com.te.lms.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
@RestController
public class BookController {

	private final BookService bookService;

	@GetMapping(path = "/book-pattern")
	public BookDTO jsonPatt(BookDTO bookDTO) {
		
		return BookDTO.builder()
				.author(AuthorDTO.builder().build())
				.publisher(PublisherDTO.builder().build())
				.genres(new ArrayList<String>()).publicationYear(LocalDate.now()).build();
	}
	
	@PostMapping(path = "/book")
	public ResponseEntity<Successresponse<String>> saveBook(
			@RequestBody BookDTO bookDTO) {
		
		log.info("saveBook(BookDTO bookDTO) called"+bookDTO);
		
		String bookId = bookService.savebook(bookDTO);
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(Successresponse
						.<String>builder()
						.data(bookId).message(LibraryConstant.BOOK_SAVED).build());
	}
	
	@GetMapping(path="/book-details")
	public ResponseEntity<Successresponse<BookDTO>> getBookDetails(@RequestParam ("title")
																	String title){
		BookDTO bookDTO = bookService.getBookDetails(title);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(Successresponse.<BookDTO>builder().data(bookDTO).build());
	}
	
	@GetMapping(path = "/all-bookdetails-list")
	public ResponseEntity<Successresponse<List>> getBookList(){
		
		List list = bookService.getAllBookDetails();
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(Successresponse.<List>builder().data(list).message("All book details").build());
	}
	
	@GetMapping(path = "/genre-based-booklist")
	public ResponseEntity<Successresponse<List>> genreBasedBookList(@RequestParam ("genreName") String genreName){
	
		List list = bookService.getBooksBasedOnGenre(genreName);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(Successresponse.<List>builder().data(list).message("All book details").build());
		
	}
	
	@GetMapping(path = "/countof-borrowed-booklist")
	public ResponseEntity<Successresponse<List>> getCountOfBookBorrowed(){
		
		List list=bookService.getCountOfBookBorrowed();
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(Successresponse.<List>builder().data(list).message("All book details with count").build());
		
	}
	
}
