package com.mycom.practice.service;

import java.util.List;


import com.mycom.practice.dto.BookDto;

public interface BookService {
	List<BookDto> list();

	BookDto detail(int isbn);

	int insert(BookDto dto);

	int update(BookDto dto);

	int delete(int isbn);
}
