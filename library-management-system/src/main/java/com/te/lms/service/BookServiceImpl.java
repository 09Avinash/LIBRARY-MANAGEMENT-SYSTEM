package com.te.lms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.te.lms.constants.LibraryConstant;
import com.te.lms.dto.BookDTO;
import com.te.lms.entity.Author;
import com.te.lms.entity.Book;
import com.te.lms.entity.Genre;
import com.te.lms.entity.Publisher;
import com.te.lms.exception.BookTitleNotAvailableException;
import com.te.lms.repository.AuthorRepository;
import com.te.lms.repository.BookRepository;
import com.te.lms.repository.GenreRepository;
import com.te.lms.repository.PublisherRepository;
import com.te.lms.utils.BookUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final PublisherRepository publisherRepository;
	private final GenreRepository genreRepository;
	

	@Override
	public String savebook(BookDTO bookDTO) {

		Optional<Book> bookfromDB = bookRepository.findByTitle(bookDTO.getTitle());

		Optional<Author> authorFromDb = authorRepository
				.findByFirstNameAndLastNameAndDateOfBirthAndCountry(
				bookDTO.getAuthor().getFirstName(),
				bookDTO.getAuthor().getLastName(),
				bookDTO.getAuthor().getDateOfBirth(),
				bookDTO.getAuthor().getCountry());
		
		Optional<Publisher> publisherFromDB = publisherRepository
				.findByNameAndLocation(
						bookDTO.getPublisher().getName(),
						bookDTO.getPublisher().getLocation());
		
		if (!bookfromDB.isPresent()) {

			Book book = BookUtil.convertBookDTOtoBook(bookDTO);
			Author author = null;
			if (authorFromDb.isPresent()) {
				author = authorFromDb.get();
				book.setAuthor(author);
			}
			Publisher publisher = null;
			if (publisherFromDB.isPresent()) {
				publisher = publisherFromDB.get();
				book.setPublisher(publisher);
			}
			bookRepository.save(book);

			return book.getBookId();
		}
		throw new BookTitleNotAvailableException(LibraryConstant.BOOK_TITLE_ALREADY_EXIST);
	}

	@Override
	public BookDTO getBookDetails(String title) {

		Optional<Book> bookfromDB = bookRepository.findByTitle(title);
		Book book = bookfromDB.get();
		return BookUtil.BookToBookDTO(book);
	}

	@Override
	public List<Book> getAllBookDetails() {
		List<Book> listOfBooksFromDB = bookRepository.findAll();
		
		List listOfBooks = listOfBooksFromDB.stream()
							.map(b->BookUtil.BookToBookDTO(b))
							.collect(Collectors.toList());
		return listOfBooks;
	}

	@Override
	public List getBooksBasedOnGenre(String genreName) {
		
		Genre genre = genreRepository.findByName(genreName);
		
		List<String> genreBasedList = genre.getBooks().stream().map(t->t.getTitle()).collect(Collectors.toList());
		
		return genreBasedList;
	}

	@Override
	public List getCountOfBookBorrowed() {
		List<Book> listOfBooksFromWithBorrowCount=bookRepository.findAll();
		
		List listOfBooks = listOfBooksFromWithBorrowCount
							.stream()
							.map(l->l.getTitle()+"  "+l.getAuthor().getFirstName()+" count"+l.getLoans().size())
							.collect(Collectors.toList());
		return listOfBooks;
	}

}
