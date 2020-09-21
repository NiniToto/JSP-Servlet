package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.exception.CustomException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.servlet01.CalculateServlet.MarchallingType;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/idCheck.do")
public class IdCheckController extends HttpServlet{
	
	private IMemberService service = MemberServiceImpl.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String mem_id = req.getParameter("memId");
		
		int sc = 200;
		String idChkMsg = null;
		
		if(StringUtils.isNotBlank(mem_id)) {
			
			boolean validId = false;
			try {
				service.retrieveMember(mem_id);
			}catch (CustomException e) {
				validId = true;
			}
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("valid", validId);
			
			if(!validId) {
				resultMap.put("useId", "추천아이디");
			}
			
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, resultMap);
			}
		}else {
			sc = 400;
			idChkMsg = "아이디를 입력하지 않았습니다.";
			
			resp.sendError(sc, idChkMsg);
			return;
		}
	}
}
