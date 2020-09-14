package kr.or.ddit.prop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prop.service.DataBasePropertyServiceImpl;
import kr.or.ddit.prop.service.IDataBasePropertyService;
import kr.or.ddit.vo.PropertyVO;

@WebServlet("/10/jdbcDesc.do")
public class DataBasePropertyControllerServlet extends HttpServlet{
	
	IDataBasePropertyService service = new DataBasePropertyServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//인터페이스를 사용하는 이유
		//1. 협업
		//2 .실행코드의 캡슐화 - 데이터 보호, 로직 보호
		String accept = req.getHeader("Accept");
		PropertyVO propertyVO = PropertyVO.getBuilder()
														.property_name(req.getParameter("property_name"))
														.property_value(req.getParameter("property_value"))
														.description(req.getParameter("description"))
														.build();
		
		List<PropertyVO> list = service.readDataBaseProperties(propertyVO);
		List<String> nameList = service.readAllProperty_names();
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			//marshalling
			ObjectMapper mapper = new ObjectMapper();
			try(
					PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, list);
			}
			
		}else {
			String goPage = "/WEB-INF/10/jdbcDesc.jsp";
			req.setAttribute("nameList", nameList);
			req.setAttribute("propList", list);
			req.setAttribute("param", propertyVO);
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}
