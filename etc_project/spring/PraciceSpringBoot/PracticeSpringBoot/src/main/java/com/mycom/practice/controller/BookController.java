package com.mycom.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.practice.dto.BookDto;
import com.mycom.practice.service.BookService;

@RestController
public class BookController {

	@Autowired
	BookService service;

	@GetMapping(value = "/books")
	public List<BookDto> list() {
		List<BookDto> list = service.list();
		System.out.println("list()");
		for (BookDto bookDto : list) {
			System.out.println(bookDto);
		}
		return list;
	}

	@GetMapping(value = "/books/{isbn}")
	public BookDto detail(@PathVariable int isbn) {
		System.out.println("detail() : " + isbn);
		return service.detail(isbn);
	}

	@PostMapping(value = "/books")
	public int insert(BookDto dto) {
		System.out.println("insert()");
		System.out.println(dto);
		return service.insert(dto);
	}

	@PutMapping(value = "/books/{isbn}")
	public int update(BookDto dto) {
		System.out.println("update()");
		System.out.println(dto);
		return service.update(dto);
	}

	@DeleteMapping(value = "/books/{isbn}")
	public int delete(@PathVariable int isbn) {
		System.out.println("delete() : " + isbn);
		return service.delete(isbn);
	}

}
