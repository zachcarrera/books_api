package com.zachcarrera.booksapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zachcarrera.booksapi.models.Book;
import com.zachcarrera.booksapi.repositories.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public List<Book> allBooks() {
		return bookRepository.findAll();
	}
	
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}
	
	public Book findBook(Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	
	
	public Book updateBook(Long id, String title, String description, String language, Integer numOfPages) {
		
		Book updatedBook = new Book(title, description, language, numOfPages);
		
		updatedBook.setId(id);
		return bookRepository.save(updatedBook);
	}
	
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
}
