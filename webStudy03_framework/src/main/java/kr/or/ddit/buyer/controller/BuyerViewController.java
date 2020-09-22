package kr.or.ddit.buyer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/buyer/buyerView.do")
public class BuyerViewController extends HttpServlet{

	private IBuyerService service = BuyerServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String what = req.getParameter("what");
		
		if(StringUtils.isBlank(what)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		
		BuyerVO buyerVO = service.retrieveBuyer(what);
		req.setAttribute("buyer", buyerVO);
		String goPage = "/WEB-INF/views/buyer/buyerView.jsp";
		req.getRequestDispatcher(goPage).forward(req, resp);
	}
}
