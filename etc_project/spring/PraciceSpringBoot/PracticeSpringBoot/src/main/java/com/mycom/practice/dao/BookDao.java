package com.mycom.practice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.practice.dto.BookDto;

@Mapper
public interface BookDao {
	List<BookDto> list();

	BookDto detail(int isbn);

	int insert(BookDto dto);

	int update(BookDto dto);

	int delete(int isbn);
}
