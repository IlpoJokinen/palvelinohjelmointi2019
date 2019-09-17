package com.example.Bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookstoreRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookstoreRepository repository;
	@Autowired
	private CategoryRepository crepository;

	@RequestMapping(value="/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		model.addAttribute("categories", crepository.findAll());
		return "booklist";
	}
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", crepository.findAll());
		return "addBook";
	}
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("book") Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id")Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../booklist";
	}
	@RequestMapping(value = "/edit/{id}", method= RequestMethod.GET)
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", repository.findById(id));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}
	
}
