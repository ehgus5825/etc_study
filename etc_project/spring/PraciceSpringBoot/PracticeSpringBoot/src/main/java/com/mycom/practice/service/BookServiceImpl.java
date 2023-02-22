package com.mycom.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.practice.dao.BookDao;
import com.mycom.practice.dto.BookDto;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDao dao;
	
	@Override
	public List<BookDto> list() {
		return dao.list();
	}

	@Override
	public BookDto detail(int isbn) {
		return dao.detail(isbn);
	}

	@Override
	public int insert(BookDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(BookDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int isbn) {
		return dao.delete(isbn);
	}

}
