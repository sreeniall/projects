package com.bookstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bookstore.controllers.BookStoreController;
import com.bookstore.dto.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootRestExampleApplication.class)
@WebAppConfiguration
public class BookStoreContollerTest {

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		//this already called from application build.
		//mockMvc.perform(get("/books/prepareDB").accept(contentType)).andExpect(status().isOk());				
	}

	@Test
	public void testGetAllBooks() throws Exception {
		MvcResult result = mockMvc.perform(get("/books").accept(contentType)).andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();

		System.out.println(content);
	}

	@Test
	public void testAddBook() throws Exception {

		String url = "/books/add?quantity=" + 2;
		Book anObject = new Book(100000001l,"Mastering åäö", "Average Swede", new BigDecimal(762.00));

		String requestJson = this.json(anObject);

		MvcResult result = mockMvc.perform(post(url).contentType(contentType).content(requestJson))
				.andExpect(status().isCreated()).andReturn();

		String content = result.getResponse().getContentAsString();

		System.out.println(content);
	}

	@Test
	public void testBuy() throws Exception {

		String url = "/books/buy";
		Book book1 = new Book(100000001l,"Mastering åäö", "Average Swede", new BigDecimal(762.00));
	
		Book book2 = new Book(100000002l,"How To Spend Money", "Rich Bloke", new BigDecimal(1000000.00));
		
		Book[] books = new Book[]{ book1, book2};			
		
		String requestJson = this.json(books);
		
		MvcResult result = mockMvc.perform(post(url).contentType(contentType).content(requestJson))
				.andExpect(status().isOk()).andReturn();
		
		
		String content = result.getResponse().getContentAsString();

		System.out.println(content);
	}

	@Configuration
	@EnableWebMvc
	public static class TestConfiguration {

		@Bean
		public BookStoreController bookStoreController() {
			return new BookStoreController();
		}

	}

	protected String json(Object o) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(o);
		return jsonInString;
	}

}
