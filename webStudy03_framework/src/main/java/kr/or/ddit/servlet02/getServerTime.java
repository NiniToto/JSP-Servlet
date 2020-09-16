package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/getServerTime.do")
public class getServerTime extends HttpServlet{

		private String pattern = "{\"now\" : \"%tc\"}";
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String accept = req.getHeader("Accept");
			String mime = "text/html; charset = UTF-8";
			if(accept.contains("json")){
				mime = "application/json;charset = UTF-8";		
			}
			resp.setContentType(mime);
			
			Calendar cal = Calendar.getInstance();
			
			String jsonData = String.format(pattern, cal);
			
			PrintWriter out = resp.getWriter();
			out.println(jsonData);
			out.close();
		}
}
