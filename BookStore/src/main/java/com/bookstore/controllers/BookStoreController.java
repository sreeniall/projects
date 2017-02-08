package com.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.dto.Book;
import com.bookstore.handler.BookStoreHandler;

@RestController
@RequestMapping("/books")
public class BookStoreController {

	@Autowired
	private BookStoreHandler bookStoreHandler;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Book[]> getBooks() {
		return new ResponseEntity<>(bookStoreHandler.list(""), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{searchString}")
	public ResponseEntity<Book[]> getBooks(@PathVariable String searchString) {
		return new ResponseEntity<>(bookStoreHandler.list(searchString), HttpStatus.OK);
	}

	@RequestMapping(consumes = "application/json", produces = "application/json", method = RequestMethod.POST, value = "/add")
	public ResponseEntity<?> addToBasket(@RequestBody Book input, @RequestParam(value = "quantity") int quantity) {
		return new ResponseEntity<>(bookStoreHandler.add(input, quantity), HttpStatus.CREATED);
	}

	@RequestMapping(consumes = "application/json", produces = "application/json", method = RequestMethod.DELETE, value = "/remove")
	public ResponseEntity<?> removeFromBasket(@RequestBody Book book) {
		return new ResponseEntity<>(bookStoreHandler.remove(book), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/buy")
	public ResponseEntity<int[]> buyItems(@RequestBody Book[] books) {
		return new ResponseEntity<>(bookStoreHandler.buy(books), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/prepareDB")
	public void prepareDB() {
		bookStoreHandler.prepareData();
	}

}
