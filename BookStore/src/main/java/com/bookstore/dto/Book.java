package com.bookstore.dto;

import java.math.BigDecimal;

public class Book implements Product {
	private Long id;
	private String title;
	private String author;
	private BigDecimal price;

	public Book() {
	}

	public Book(Long id, String title, String author, BigDecimal price) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
