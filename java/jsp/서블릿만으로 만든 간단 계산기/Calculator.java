package com.dohyun.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 브라우저에 저장되어 있던 cookie를 받음
		Cookie[] cookies = request.getCookies();
		
		// exp 는 계속 지속되는 수식 값 / exp를 쿠키 값 중에서 찾음
		String exp = "0";
		if(cookies != null)
			for(Cookie c : cookies)
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
		
		// html 태그들을 문자열로 wirter을 통해서 출력 => 이게 곧 html 코드 / 동적인 페이지
		PrintWriter out = response.getWriter();
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("<style>");
		out.write("input{");
		out.write("	width:50px;");
		out.write("	height:50px;");
		out.write("}");
		out.write(".output {");
		out.write("	height:50px;");
		out.write("	background: #e9e9e9;");
		out.write("	font-size:24px;");
		out.write("	font-weight:bold;");
		out.write("	text-align :right;");
		out.write("	padding : 0px 10px");
		out.write("}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("	<form action=\"calculator\" method=\"post\">");
		out.write("		<table>");
		out.write("			<tr>");
		// printf 메소드를 통해서 exp 값을 형식문자열을 통해서 변화를 줄 수 있음. 
		out.printf("				<td class=\"output\" colspan=\"4\">%s</td>", exp);
		out.write("			<tr>");
		out.write("			<tr>");
		out.write("				<td></td>");
		out.write("				<td><input type=\"submit\" name=\"oper\" value=\"C\"></td>");
		out.write("				<td><input type=\"submit\" name=\"oper\" value=\"BS\"></td>");
		out.write("				<td><input type=\"submit\" name=\"oper\" value=\"/\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"num\" value=\"7\"></td>");
		out.write("				<td><input type=\"submit\" name=\"num\" value=\"8\"></td>");
		out.write("				<td><input type=\"submit\" name=\"num\" value=\"9\"></td>");
		out.write("				<td><input type=\"submit\" name=\"oper\" value=\"*\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"num\" value=\"4\"></td>");
		out.write("				<td><input type=\"submit\" name=\"num\" value=\"5\"></td>");
		out.write("				<td><input type=\"submit\" name=\"num\" value=\"6\"></td>");
		out.write("				<td><input type=\"submit\" name=\"oper\" value=\"-\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"num\" value=\"1\"></td>");
		out.write("				<td><input type=\"submit\" name=\"num\" value=\"2\"></td>");
		out.write("				<td><input type=\"submit\" name=\"num\" value=\"3\"></td>");
		out.write("				<td><input type=\"submit\" name=\"oper\" value=\"+\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td></td>");
		out.write("				<td><input type=\"submit\" name=\"num\" value=\"0\"></td>");
		out.write("				<td><input type=\"submit\" name=\"dot\" value=\".\"></td>");
		out.write("				<td><input type=\"submit\" name=\"oper\" value=\"=\"></td>");
		out.write("			</tr>");
		out.write("		</table>");
		out.write("	</form>");
		out.write("</body>");
		out.write("</html>");		
	}	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 브라우저에 저장되어 있던 cookie를 받음
		Cookie[] cookies = request.getCookies();
		
		// Calcpage에서 사용자가 입력한 내용
		String number = request.getParameter("num");
		String operator = request.getParameter("oper");
		String dot = request.getParameter("dot");
		
		// 쿠키에서 exp 를 찾는 것
		String exp = "";
		if(cookies != null)
			for(Cookie c : cookies)
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
				
		// 사용자가 입력한 연산자가 "=" 라면 
		if(operator != null && operator.equals("=")) {
			int end = exp.length() - 1;
			if(exp.charAt(end) != '+' && exp.charAt(end) != '-' && exp.charAt(end) != '*' && exp.charAt(end) != '/' && exp.charAt(end) != '.') {
				// "=" 를 입력했기 때문에 연산을 실행
				ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
				try {
					// 연산 값을 String 으로 exp 에 담음
					exp = String.valueOf(engine.eval(exp));
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// 사용자가 입력한 연산자가 "C" 라면
		else if(operator != null && operator.equals("C")) {
			exp = "";
		}
		// 사용자가 입력한 연산자가 "BS" 라면
		else if(operator != null && operator.equals("BS")) {
			if(exp.length() > 1)
				exp = exp.substring(0, exp.length() - 1);
			else
				exp = "";
		}
		// 사용자가 입력한 연산자가 그 외의 것이라면
		else {
			// 수식이 아무것도 없다면
			if(exp == "") {
				exp += (number == null)? "" : number;
				exp += (operator == null)? "" : operator;
				exp += (dot == null)? "" : dot;
			}
			// 수식이 있다면
			else {
				int end = exp.length() - 1;
				// 가장 마지막 문자가 연산자라면
				if(exp.charAt(end) == '+' || exp.charAt(end) == '-' || exp.charAt(end) == '/' || exp.charAt(end) == '*' ) {
					exp += (number == null)? "" : number;
				}
				// 가장 마지막 문자가 "."이라면
				else if(exp.charAt(end) == '.') {
					exp += (number == null)? "" : 	number;
				}
				// 가장 마지막 문자가 숫자라면
				else {
					exp += (number == null)? "" : number;
					exp += (operator == null)? "" : operator;
					exp += (dot == null)? "" : dot;
				}
			}
		}
		
		Cookie expCookie = new Cookie("exp", exp);
		// 사용자의 연산자가 C여서 초기화를 하거나 BS로 다 지워버리면
		if(exp == "")
			expCookie.setMaxAge(0);
		// expCookie를 변경하였으니 저장함
		expCookie.setPath("/calculator");
		response.addCookie(expCookie);
		response.sendRedirect("calculator");
	}
}