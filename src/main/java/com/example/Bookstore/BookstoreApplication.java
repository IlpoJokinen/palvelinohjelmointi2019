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
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner runner(BookstoreRepository repository, CategoryRepository crepository, UserRepository urepository) {
	return (args) -> {
		log.info("save a couple of books");

		crepository.save(new Category("Military"));
		crepository.save(new Category("Novel"));
		
		repository.save(new Book("A Farewell to Arms", "Ernest Hemingway", "1232323-21", 1929, 20.00, crepository.findByName("Military").get(0)));
		repository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 1945, 20.00, crepository.findByName("Novel").get(0)));
		
		User user1 = new User((long)1000, "user", "$2a$10$m6l4T0Y2oDrzBPm6U9dEoemgcDyqorzqKi.XxI0dqrFgKec7FbVKC", "USER", "user.test@gmail.com");
		User user2 = new User((long)2000, "admin", "$2a$10$/Dd3OSW2ynPvK2SXkgSgVOGsttxlGkcCAMrWgWy9v7ofaIXs0xJqe", "ADMIN", "admin.test@gmail.com");
		
		urepository.save(user1);
		urepository.save(user2);
		
		log.info("fetch all books");
		for (Book book : repository.findAll()) {
			log.info(book.toString());
		}
	};
	}
}

