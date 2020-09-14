package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/registMember.do")
public class MemberRegistController extends HttpServlet{
	
	private IMemberService service =MemberServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/member/registForm.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String mem_id = req.getParameter("mem_id");
//		String mem_pass = req.getParameter("mem_pass");
//		String mem_name = req.getParameter("mem_name");
//		String mem_regno1 = req.getParameter("mem_regno1");
//		String mem_regno2 = req.getParameter("mem_regno2");
//		String mem_bir = req.getParameter("mem_bir");
//		String mem_zip = req.getParameter("mem_zip");
//		String mem_add1 = req.getParameter("mem_add1");
//		String mem_add2 = req.getParameter("mem_add2");
//		String mem_hometel = req.getParameter("mem_hometel");
//		String mem_comtel = req.getParameter("mem_comtel");
//		String mem_hp = req.getParameter("mem_hp");
//		String mem_mail = req.getParameter("mem_mail");
//		String mem_job = req.getParameter("mem_job");
//		String mem_like = req.getParameter("mem_like");
//		String mem_memorial = req.getParameter("mem_memorial");
//		String mem_memorialday = req.getParameter("mem_memorialday");
//		String mem_mileage = req.getParameter("mem_mileage");
//		String mem_delete = req.getParameter("mem_delete");
		
		MemberVO memberVO = MemberVO.builder()
						.mem_id(req.getParameter("mem_id"))
						.mem_pass(req.getParameter("mem_pass"))
						.mem_name(req.getParameter("mem_name"))
						.mem_regno1(req.getParameter("mem_regno1"))
						.mem_regno2(req.getParameter("mem_regno2"))
						.mem_bir(req.getParameter("mem_bir"))
						.mem_zip(req.getParameter("mem_zip"))
						.mem_add1(req.getParameter("mem_add1"))
						.mem_add2(req.getParameter("mem_add2"))
						.mem_hometel(req.getParameter("mem_hometel"))
						.mem_comtel(req.getParameter("mem_comtel"))
						.mem_hp(req.getParameter("mem_hp"))
						.mem_mail(req.getParameter("mem_mail"))
						.mem_job(req.getParameter("mem_job"))
						.mem_like(req.getParameter("mem_like"))
						.mem_memorial(req.getParameter("mem_memorial"))
						.mem_memorialday(req.getParameter("mem_memorialday"))
						.mem_mileage(Integer.parseInt(req.getParameter("mem_mileage")))
						.mem_delete(req.getParameter("mem_delete"))
						.build();
		
		if(memberVO != null) {
			ServiceResult serviceResult = service.registMember(memberVO);
			
			req.setAttribute("serviceResult", serviceResult);
			req.setAttribute("memberVO", memberVO);
			req.getRequestDispatcher("").forward(req, resp);
			
		}
	}
}
