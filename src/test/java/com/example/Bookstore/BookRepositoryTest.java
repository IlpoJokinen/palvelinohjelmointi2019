package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookstoreRepository;
import com.example.Bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookstoreRepository repository;
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = repository.findByTitle("A Farewell to Arms");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("A Farewell to Arms");
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("Testi kirja", "Ilpo Jokinen", "1111-222", 2019, 300.00, new Category("Upea"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
}
