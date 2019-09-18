package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookstoreRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner runner(BookstoreRepository repository, CategoryRepository crepo) {
	return (args) -> {
		log.info("save a couple of books");

		crepo.save(new Category("Military"));
		crepo.save(new Category("Novel"));
		
		repository.save(new Book("A Farewell to Arms", "Ernest Hemingway", "1232323-21", 1929, 20.00, crepo.findByName("Military").get(0)));
		repository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 1945, 20.00, crepo.findByName("Novel").get(0)));
		
		log.info("fetch all books");
		for (Book book : repository.findAll()) {
			log.info(book.toString());
		}
	};
	}
}

