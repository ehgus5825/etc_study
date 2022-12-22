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
		// �������� ����Ǿ� �ִ� cookie�� ����
		Cookie[] cookies = request.getCookies();
		
		// exp �� ��� ���ӵǴ� ���� �� / exp�� ��Ű �� �߿��� ã��
		String exp = "0";
		if(cookies != null)
			for(Cookie c : cookies)
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
		
		// html �±׵��� ���ڿ��� wirter�� ���ؼ� ��� => �̰� �� html �ڵ� / ������ ������
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
		// printf �޼ҵ带 ���ؼ� exp ���� ���Ĺ��ڿ��� ���ؼ� ��ȭ�� �� �� ����. 
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
		// �������� ����Ǿ� �ִ� cookie�� ����
		Cookie[] cookies = request.getCookies();
		
		// Calcpage���� ����ڰ� �Է��� ����
		String number = request.getParameter("num");
		String operator = request.getParameter("oper");
		String dot = request.getParameter("dot");
		
		// ��Ű���� exp �� ã�� ��
		String exp = "";
		if(cookies != null)
			for(Cookie c : cookies)
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
				
		// ����ڰ� �Է��� �����ڰ� "=" ��� 
		if(operator != null && operator.equals("=")) {
			int end = exp.length() - 1;
			if(exp.charAt(end) != '+' && exp.charAt(end) != '-' && exp.charAt(end) != '*' && exp.charAt(end) != '/' && exp.charAt(end) != '.') {
				// "=" �� �Է��߱� ������ ������ ����
				ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
				try {
					// ���� ���� String ���� exp �� ����
					exp = String.valueOf(engine.eval(exp));
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// ����ڰ� �Է��� �����ڰ� "C" ���
		else if(operator != null && operator.equals("C")) {
			exp = "";
		}
		// ����ڰ� �Է��� �����ڰ� "BS" ���
		else if(operator != null && operator.equals("BS")) {
			if(exp.length() > 1)
				exp = exp.substring(0, exp.length() - 1);
			else
				exp = "";
		}
		// ����ڰ� �Է��� �����ڰ� �� ���� ���̶��
		else {
			// ������ �ƹ��͵� ���ٸ�
			if(exp == "") {
				exp += (number == null)? "" : number;
				exp += (operator == null)? "" : operator;
				exp += (dot == null)? "" : dot;
			}
			// ������ �ִٸ�
			else {
				int end = exp.length() - 1;
				// ���� ������ ���ڰ� �����ڶ��
				if(exp.charAt(end) == '+' || exp.charAt(end) == '-' || exp.charAt(end) == '/' || exp.charAt(end) == '*' ) {
					exp += (number == null)? "" : number;
				}
				// ���� ������ ���ڰ� "."�̶��
				else if(exp.charAt(end) == '.') {
					exp += (number == null)? "" : 	number;
				}
				// ���� ������ ���ڰ� ���ڶ��
				else {
					exp += (number == null)? "" : number;
					exp += (operator == null)? "" : operator;
					exp += (dot == null)? "" : dot;
				}
			}
		}
		
		Cookie expCookie = new Cookie("exp", exp);
		// ������� �����ڰ� C���� �ʱ�ȭ�� �ϰų� BS�� �� ����������
		if(exp == "")
			expCookie.setMaxAge(0);
		// expCookie�� �����Ͽ����� ������
		expCookie.setPath("/calculator");
		response.addCookie(expCookie);
		response.sendRedirect("calculator");
	}
}