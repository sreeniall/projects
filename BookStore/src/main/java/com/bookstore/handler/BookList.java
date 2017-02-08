package com.bookstore.handler;

import com.bookstore.dto.Book;

public interface BookList {  
	   public Book[] list(String searchString);
	   public boolean add(Book book, int quantity);
	   public int[] buy(Book... books);
	}
