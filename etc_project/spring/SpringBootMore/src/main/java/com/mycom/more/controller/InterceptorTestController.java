package com.mycom.more.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// vue - axios library <- http 통신 library
// axios library 에서 http 요청을 보낼 때 credentials 설정 ( true : 기존 세션 유지 / false : 항상 새로운 sessionid 전송 )

// 이후에, vue-cli 로 개발 시 백엔드는 아래와 같이 origins = vue-cli 개발 서버 정보를, allowCredentails="true" 로 설정해야
// sessionid 가 지속되고 이를 통해서 로그인 등 서버세션을 사용할 수 있다.
// 서버세션을 사용하지 않을려면 jwt 등 별도의 state 저장 수단을 이용해야 함. 그럴 경우는 위 설정이 필요 X
@CrossOrigin(
		origins = "http://127.0.0.1:5500", 
		allowCredentials = "true", // sessionid 기존처럼 고정 => server session 계속 사용
		allowedHeaders = "*",
		methods = {
				RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT ,RequestMethod.DELETE,
				RequestMethod.OPTIONS, RequestMethod.HEAD
				}
		)
@RestController // 무조건 json response
public class InterceptorTestController {

	@GetMapping("/login") // login page 이동
	public String m1() {
		System.out.println("/login");
		return "/login"; // jsp 로 분기 X. 문자열 그대로 json으로 response. restController니까
	}

	@GetMapping("/login/ok") // login 성공 시뮬레이션
	public String m2(HttpSession session) {
		System.out.println("/login/ok");
		session.setAttribute("login", "success");
		return "/login/ok";
	}
	
	@GetMapping("/logout") // logout
	public String m3(HttpSession session) {
		System.out.println("/logout");
		session.invalidate();
		return "/logout";
	}
	
	@GetMapping("/users") // 일반 화면 - 로그인해야 갈 수 있는 화면
	public String m3( ) {
		System.out.println("/users");
		return "/users";
	}
}
