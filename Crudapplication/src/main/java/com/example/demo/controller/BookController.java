package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.repo.BookRepo;
import com.example.demo.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		System.out.println("click here");
		try {
			List<Book>bookList= new ArrayList();
			bookService.findAll().forEach(bookList::add);
			
			if(bookList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(bookList,HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/getBookById/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Optional <Book> bookData=bookService.findById(id);
		
		if(bookData.isPresent()) {
			return new ResponseEntity<>(bookData.get(),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book bookObj = bookService.save(book);
		
		return new ResponseEntity<>(bookObj, HttpStatus.OK);
	}
	
	@PostMapping("/updateBookById/{id}")
	public ResponseEntity<Book> updateBoohById(@PathVariable Long id, @RequestBody Book newBookData) {
		Optional<Book> oldBookData =bookService.findById(id);
		
		if(oldBookData.isPresent()) {
			Book updatedBookData = oldBookData.get();
			updatedBookData.setTitle(newBookData.getTitle());
			updatedBookData.setAuthor(newBookData.getAuthor());
			
			
			Book bookObj=bookService.save(updatedBookData);
			return new ResponseEntity<>(bookObj, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/deleteBookById/{id}")
	public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id) {
		bookService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}

}
