package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Book;
import com.example.demo.repo.BookRepo;

@Service
@Transactional
public class BookService {
	@Autowired
	private BookRepo bookRepo;
	
	public List<Book> findAll(){
		return bookRepo.findAll();
	}

	public Optional<Book> findById(Long id) {
		// TODO Auto-generated method stub
		return bookRepo.findById(id);
	}

	public Book save(Book book) {
		// TODO Auto-generated method stub
		return bookRepo.save(book);
	}

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		bookRepo.deleteById(id);
	}
	

}
