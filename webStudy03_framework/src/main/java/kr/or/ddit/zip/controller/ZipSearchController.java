package kr.or.ddit.zip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.ZipVO;
import kr.or.ddit.zip.service.IZipService;
import kr.or.ddit.zip.service.ZipServiceImpl;

@WebServlet("/zipSearch.do")
public class ZipSearchController extends HttpServlet{

	private IZipService service = ZipServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String accept = req.getHeader("Accept");
		String mime = null;
		if (accept.contains("json")) {
			mime = "application/json;charset=UTF-8";
		}
		resp.setContentType(mime);
		
		String zipInput = req.getParameter("zipInput");
		
		List<ZipVO> list = service.zipSearch(zipInput);
		
		ObjectMapper mapper = new ObjectMapper();
		
		try(
			PrintWriter out = resp.getWriter();	
		){
			mapper.writeValue(out, list);
		}
	}
}
