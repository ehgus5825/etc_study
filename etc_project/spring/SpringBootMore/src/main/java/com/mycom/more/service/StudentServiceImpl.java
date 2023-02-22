package com.mycom.more.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.more.dao.StudentDao;
import com.mycom.more.dto.StudentDto;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentDao dao;
	
	@Override
	public List<StudentDto> list() {
		return dao.list();
	}

	@Override
	public StudentDto detail(int studentId) {
		// TODO Auto-generated method stub
		return dao.detail(studentId);
	}

//	@Transactional -> 그래서 어노테이션을 쓰기보다 이를 처리하는 클래스를 만들어 처리 (비용때문에) AOP 활용->TransactionAspect.java 작성 ( gradle springboot strat aop 설치)
	@Override
	public int insert(StudentDto dto) {
//		dao.insert(dto);
		
		// unchekced exception
//		String str = null;
//		str.length();
		
		
		return dao.insert(dto);
	}

//	@Transactional // 비용(자원)이 비싼 어노테이션 => 반드시 필요한 경우에만 사용
	@Override
	public int update(StudentDto dto) {
//		dao.update(dto);
//		
//		// unchekced exception
//		String str = null;
//		str.length();
//		
//		// dto setter 를 이용해 두번 수정
//		
//		dto.setPhone("11");
		
		return dao.update(dto);
	}

	@Override
	public int delete(int studentId) {
		// TODO Auto-generated method stub
		return dao.delete(studentId);
	}

}
