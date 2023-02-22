package com.mycom.more;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mycom.more.dto.StudentDto;

@SpringBootApplication
public class SpringBootMoreApplication {

	public static void main(String[] args) {
		StudentDto dto = new StudentDto(111,"nayu","na@yu.com", "010-111-111");;
		SpringApplication.run(SpringBootMoreApplication.class, args);
	}

}
