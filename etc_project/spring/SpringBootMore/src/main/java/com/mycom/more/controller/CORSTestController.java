package com.mycom.more.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin("*") // 모든 서버에 대해  모든 처리 허락
// 서버 세션 ( 로그인 etc) 같은 경우 전체 허락하면 오류 발생 => cookie를 통해서 전송되는 sessionid 계속 변경

@CrossOrigin(
		origins = "http://127.0.0.1:5500", 
		allowCredentials = "true", // sessionid 기존처럼 고정 => server session 계속 사용
		allowedHeaders = "*",
		methods = {
				RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT ,RequestMethod.DELETE,
				RequestMethod.OPTIONS, RequestMethod.HEAD
				}
		)
@RestController
public class CORSTestController {
	
	@GetMapping("/getcors")
	public ResponseEntity<Map<String, String>> getCORS(String param) {
		System.out.println("getCORS : param : " + param);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "success");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

	@PostMapping("/postcors")
	public ResponseEntity<Map<String, String>> postCORS(String param) {
		System.out.println("potCORS : param : " + param);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "success");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}
	
	@PutMapping("/putcors/{num}")
	public ResponseEntity<Map<String, String>> putCORS(@PathVariable int num) {
		System.out.println("putCORS : num : " + num);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "success");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletecors/{num}")
	public ResponseEntity<Map<String, String>> deleteCORS(@PathVariable int num) {
		System.out.println("putCORS : num : " + num);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "success");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

}
