package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

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
		req.setCharacterEncoding("UTF-8");
//		1. 요청 파라미터 획득
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(member, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(member);
//		2. 검증(DB 스키마 구조 참고)
		Map<String, StringBuffer> errors = new LinkedHashMap<String, StringBuffer>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);
		
		String goPage = null;
		boolean redirect = false;
		String message = null;
		
		if(valid) {
	//		3. 통과
	//		4. 통과한 경우, 로직을 이용한 등록
			ServiceResult result = service.registMember(member);
			switch (result) {
			case PKDUPLICATED:
				goPage = "/WEB-INF/views/member/registForm.jsp";
				message = "아이디 중복, 확인 후 다시 넣으세요!";
				break;
			case FAILED:
				goPage = "/WEB-INF/views/member/registForm.jsp";
				message = "서버 문제로 등록이 완료되지 않았습니다. 잠시 후 다시 시도해주세요!";
				break;
			default:
				goPage = "/login/loginForm.jsp";
				redirect = true;
				break;
			}
		}else {
//			불통
//			등록 이후의 경우의 수에 대한 처리
			goPage = "/WEB-INF/views/member/registForm.jsp";
			
		}
		
		req.setAttribute("message", message);
		
		if(redirect) {
			resp.sendRedirect(req.getContextPath() + goPage);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}

	private boolean validate(MemberVO member, Map<String, StringBuffer> errors ) {
		//타입, 필수여부, 길이, 형식...
		boolean valid = true;
		if (StringUtils.isBlank(member.getMem_id())) {
			valid = false;
			errors.put("mem_id", new StringBuffer("아이디 필수데이터 누락"));
		}
		if (StringUtils.isBlank(member.getMem_pass())) {
			valid = false;
			errors.put("mem_pass", new StringBuffer("비밀번호 필수데이터 누락"));
		}
		if (StringUtils.isBlank(member.getMem_name())) {
			valid = false;
			errors.put("mem_name", new StringBuffer("회원명 필수데이터 누락"));
		}
		if (StringUtils.isBlank(member.getMem_regno1())) {
			valid = false;
			errors.put("mem_regno1", new StringBuffer("주민번호1 필수데이터 누락"));
		}
		if (StringUtils.isBlank(member.getMem_regno2())) {
			valid = false;
			errors.put("mem_regno2", new StringBuffer("주민번호2 필수데이터 누락"));
		}
		if (StringUtils.isBlank(member.getMem_zip())) {
			valid = false;
			errors.put("mem_zip", new StringBuffer("우편번호 필수데이터 누락"));
		}
		if (StringUtils.isBlank(member.getMem_add1())) {
			valid = false;
			errors.put("mem_add1", new StringBuffer("주소1 필수데이터 누락"));
		}
		if (StringUtils.isBlank(member.getMem_add2())) {
			valid = false;
			errors.put("mem_add2", new StringBuffer("주소2 필수데이터 누락"));
		}
		if (StringUtils.isBlank(member.getMem_hometel())) {
			valid = false;
			errors.put("mem_hometel", new StringBuffer("집번호 필수데이터 누락"));
		}
		if (StringUtils.isBlank(member.getMem_comtel())) {
			valid = false;
			errors.put("mem_comtel", new StringBuffer("회사번호 필수데이터 누락"));
		}
		if (StringUtils.isBlank(member.getMem_mail())) {
			valid = false;
			errors.put("mem_mail", new StringBuffer("메일 필수데이터 누락"));
		}
		return valid;
	}
}
