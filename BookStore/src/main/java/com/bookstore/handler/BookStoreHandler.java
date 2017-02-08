package com.bookstore.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bookstore.dto.Book;
import com.bookstore.utils.StoreUtil;

@Service
public class BookStoreHandler implements BookList {

	@Override
	public Book[] list(String searchString) {

		return fetchByString(searchString);
	}

	@Override
	public boolean add(Book book, int quantity) {

		if (StoreUtil.isAvialble(book, quantity)) {
			/*
			 * Here we add book to basket if its available on stock
			 * 
			 */
			
			int val = StoreUtil.stocks.get(book.getId()).intValue() - 1;
			StoreUtil.stocks.put(book.getId(), val);
			
			return StoreUtil.addToBasket(book, quantity);
			
			
		} else {
			return false;
		}
	}

	public boolean remove(Book book) {

		if (StoreUtil.order != null && StoreUtil.order.getItems().size() > 0) {
			return StoreUtil.removeFromBasket(book);
		} else {
			return false;
		}

	}

	@Override
	public int[] buy(Book... books) {

		int[] status = new int[books.length];

		try {

			for (int i = 0; i < books.length; i++) {

				int val = StoreUtil.stocks.get(books[i].getId()).intValue() - 1;
				StoreUtil.stocks.put(books[i].getId(), val);

				Integer stock = StoreUtil.stocks.get(books[i].getId());
				if (stock == null) {
					status[i] = BookAvailableStatus.DOES_NOT_EXIST.getVal();
				} else {
					status[i] = (stock > 0) ? BookAvailableStatus.OK.getVal()
							: BookAvailableStatus.NOT_IN_STOCK.getVal();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	private Book[] fetchByString(String searchString) {
		Book[] availableBooks = null;
		try {

			if ((searchString != null) && !(searchString.equals(""))) {
				List<Book> books1 = StoreUtil.books.stream()
						.filter((x) -> x.getAuthor().toUpperCase().contains(searchString.toUpperCase()))
						.collect(Collectors.toList());

				List<Book> books2 = StoreUtil.books.stream()
						.filter((x) -> x.getTitle().toUpperCase().contains(searchString.toUpperCase()))
						.collect(Collectors.toList());

				books1.addAll(books2);
				availableBooks = (Book[]) books1.toArray(new Book[books1.size()]);

			} else {
				availableBooks = StoreUtil.books.stream().toArray(Book[]::new);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return availableBooks;
	}

	public void prepareData() {
		StoreUtil.prepareData();
	}

}
