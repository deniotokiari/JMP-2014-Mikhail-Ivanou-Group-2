package com.epam.jmp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.jmp.model.Book;
import com.epam.jmp.service.IBookService;

@Controller
public class BooksController {

	private IBookService bookService;

	@Autowired
	public BooksController(IBookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(value = "/books/{id}")
	public @ResponseBody Book getBook(@PathVariable("id") String id) {

		Book book= bookService.getById(Long.valueOf(id));
		if (book != null) {
			return book;
		}
		return null;
	}

}
