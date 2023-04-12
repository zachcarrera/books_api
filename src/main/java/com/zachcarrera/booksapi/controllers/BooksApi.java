package com.zachcarrera.booksapi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zachcarrera.booksapi.models.Book;
import com.zachcarrera.booksapi.services.BookService;

@RestController
@RequestMapping("/api")
public class BooksApi {
	
	private final BookService bookService;
	
	public BooksApi(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/books")
	public List<Book> index() {
		return bookService.allBooks();
	}
	
	@RequestMapping("/books/{id}")
    public Book show(@PathVariable("id") Long id) {
        return bookService.findBook(id);
    }
	
	@PostMapping("/books")
	public Book create(
			@RequestParam(value="title") String title,
			@RequestParam(value="description") String description,
			@RequestParam(value="language") String language,
			@RequestParam(value="pages") Integer numOfPages
			) {
		Book book = new Book(title, description, language, numOfPages);
		return bookService.createBook(book);
	}
	
	
	@PutMapping("/books/{id}")
	public Book update(
			@PathVariable("id") Long id,
			@RequestParam(value="title") String title,
			@RequestParam(value="description") String description,
			@RequestParam(value="language") String language,
			@RequestParam(value="pages") Integer numOfPages
			) {
		return bookService.updateBook(id, title, description, language, numOfPages);
	}
	
	@DeleteMapping("/books/{id}")
	public void destroy(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
	}

}
