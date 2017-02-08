package com.bookstore.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.bookstore.dto.Book;
import com.bookstore.dto.Product;
import com.bookstore.handler.Order;
import com.bookstore.handler.OrderItem;

@Service
public class StoreUtil {

	public static Map<Long, Integer> stocks = new HashMap<Long, Integer>();
	public static List<Book> books = new ArrayList<Book>();
	public static Order order = new Order();;
	public static Random random = new Random();

	public static void prepareData() {

		Book book1 = new Book();
		book1.setId(100000001l);
		book1.setAuthor("Average Swede");
		book1.setTitle("Mastering åäö");
		book1.setPrice(new BigDecimal(762.00));

		books.add(book1);
		stocks.put(book1.getId(), 15);

		Book book2 = new Book();
		book2.setId(100000002l);
		book2.setAuthor("Rich Bloke");
		book2.setTitle("How To Spend Money");
		book2.setPrice(new BigDecimal(1000000.00));
		books.add(book2);
		stocks.put(book2.getId(), 1);

		Book book3 = new Book();
		book3.setId(100000003l);
		book3.setAuthor("First Author");
		book3.setTitle("Generic Title");
		book3.setPrice(new BigDecimal(185.50));

		books.add(book3);
		stocks.put(book3.getId(), 5);

		Book book4 = new Book();
		book4.setId(100000004l);
		book4.setAuthor("Second Author");
		book4.setTitle("Generic Title");
		book4.setPrice(new BigDecimal(1748.00));

		books.add(book4);
		stocks.put(book4.getId(), 3);

		Book book5 = new Book();
		book5.setId(100000005l);
		book5.setAuthor("Cunning Bastard");
		book5.setTitle("Random Sales");
		book5.setPrice(new BigDecimal(999.00));

		books.add(book5);
		stocks.put(book5.getId(), 20);

		Book book6 = new Book();
		book6.setId(100000006l);
		book6.setAuthor("Cunning Bastard");
		book6.setTitle("Random Sales");
		book6.setPrice(new BigDecimal(499.50));

		books.add(book6);
		stocks.put(book5.getId(), 3);

		Book book7 = new Book();
		book7.setId(100000007l);
		book7.setAuthor("Rich Bloke");
		book7.setTitle("Desired");
		book7.setPrice(new BigDecimal(564.50));
		books.add(book7);
		stocks.put(book7.getId(), 0);

	}

	public static boolean addToBasket(Product product, int quantity) {

		if (product != null && quantity > 0) {

			// Order in basket mode
			OrderItem item = new OrderItem();
			item.setItemId(random.nextLong());
			item.setProduct(product);
			item.setPrice(product.getPrice().multiply(new BigDecimal(quantity)));
			item.setQuantity(quantity);

			order.setOrderId(random.nextLong());
			order.getItems().add(item);
			order.setCreatedDate(new Date());
			order.setTotalPrice(order.getTotalPrice().add(item.getPrice()));

		} else {
			return false;
		}

		return true;
	}

	public static boolean isAvialble(Book book, int quantity) {

		Book result = books.stream().filter((x) -> book.getId().equals(x.getId())
													&& book.getTitle().equals(x.getTitle()) 
													&& book.getAuthor().equals(x.getAuthor())
													&& book.getPrice().equals(x.getPrice())).findAny()
													.orElse(null);
		
		return ((result != null) && (stocks.get(result.getId()) >= quantity));
	}

	
	/*
	 * This method will remove book from the basket
	 */
	public static boolean removeFromBasket(Book book) {
		
		OrderItem result = order.getItems().stream().filter((x) -> book.getId().equals(x.getItemId())).findAny().orElse(null);
		
		return order.getItems().remove(result);
	}
	
	public static void cleanData() {
		stocks = new HashMap<Long, Integer>();
		books = new ArrayList<Book>();
		order = new Order();;
		random = new Random();
	}

}
