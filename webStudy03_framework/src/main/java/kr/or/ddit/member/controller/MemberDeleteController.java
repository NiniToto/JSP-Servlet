package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/leaveApp.do")
public class MemberDeleteController extends HttpServlet{

	private IMemberService servie = MemberServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		
		String pass1 = req.getParameter("passForDel1");
		String pass2 = req.getParameter("passForDel2");
		
		int sc = 200;
		String goPage = null;
		String message = null;
		boolean redirect = false;
		
		//비밀번호 입력이 누락된 경우
		if(StringUtils.isBlank(pass1) || StringUtils.isBlank(pass2)) {
			sc = 400;
			message = "필수 입력 파라미터가 누락되었습니다. 비밀번호 입력을 확인해주세요!"; 
			goPage = "/WEB-INF/views/member/mypage.jsp";
		}
		
		//서로 다른 비밀번호를 입력한 경우
		if(!pass1.equals(pass2)) {
			message = "서로 다른 비밀번호를 입력하였습니다.";
			goPage = "/WEB-INF/views/member/mypage.jsp";
		}
		
		//틀린 비밀번호를 동일하게 입력한 경우
		if(pass1.equals(pass2) && !member.getMem_pass().equals(pass1)) {
			message = "입력한 비밀번호가 올바르지 않습니다.";
			goPage = "/WEB-INF/views/member/mypage.jsp";
		}
		
		//정상적으로 입력한 경우 - 이미 검증된 회원
		if(pass1.equals(pass2) && member.getMem_pass().equals(pass1)) {
			
			ServiceResult result = servie.removeMember(member);
			
			switch (result) {
			case INVALIDPASSWORD:
				goPage = "/WEB-INF/views/member/mypage.jsp";
				message = "비밀번호 오류";
				break;
			case FAILED:
				goPage = "/WEB-INF/views/member/mypage.jsp";
				message = "서버 문제로 수정이 완료되지 않았습니다 잠시 후 다시 시도해주세요.";
				break;
			default:
				goPage = "/login/loginForm.jsp";
				redirect = true;
				break;
			}
		}
		
		if(redirect) {
			resp.sendRedirect(req.getContextPath() + goPage);
		}else {
			req.setAttribute("message", message);
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}
