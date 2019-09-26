package com.example.Bookstore.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookstoreRepository;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookstoreRepository repository;
	@Autowired
	private CategoryRepository crepository;
	
	//Show all books
	@RequestMapping(value="/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		model.addAttribute("categories", crepository.findAll());
		return "booklist";
	}
	//Show login
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	//RESTful service to get all books
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return(List<Book>)repository.findAll();
	}
	//RESTful service to get books by id
	@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id")Long id) {
		return repository.findById(id);
	}
	//Add new book
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", crepository.findAll());
		return "addBook";
	}
	//Save new book
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("book") Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	//Delete book
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteBook(@PathVariable("id")Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../booklist";
	}
	//Edit book
	@RequestMapping(value = "/edit/{id}", method= RequestMethod.GET)
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", repository.findById(id));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}
	
}
