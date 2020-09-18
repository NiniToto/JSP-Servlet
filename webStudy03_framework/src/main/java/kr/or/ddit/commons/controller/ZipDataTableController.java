package kr.or.ddit.commons.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.commons.dao.IZipCodeSearchDAO;
import kr.or.ddit.commons.dao.ZipCodeSearchDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZipCodeVO;
import kr.or.ddit.zip.service.IZipService;
import kr.or.ddit.zip.service.ZipServiceImpl;

@WebServlet("/searchZip_DT.do")
public class ZipDataTableController extends HttpServlet{

	private IZipCodeSearchDAO dao = ZipCodeSearchDAOImpl.getInstance();
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String pageParam = req.getParameter("page");
		String searchWord = req.getParameter("search[value]"); //검색어
		String draw = req.getParameter("draw"); //현재 페이지??
		String startParam = req.getParameter("start"); //시작레코드
		String lengthParam = req.getParameter("length"); //screenSize
		
		PagingVO<ZipCodeVO> pagingVO = new PagingVO<>();
		int currentPage = 1;
		if(StringUtils.isNumeric(startParam) && StringUtils.isNumeric(lengthParam)) {
			int start = Integer.parseInt(startParam);
			int length = Integer.parseInt(lengthParam);
			pagingVO.setScreenSize(length);
			currentPage = (start + length) / length;
		}
		
		//검색전 전체 레코드 수
		int totalRecordNonSearch = dao.selectTotalCount(pagingVO);
		
		//검색 후 레코드 수
		pagingVO.setSearchWord(searchWord);
		int totalRecord = dao.selectTotalCount(pagingVO);
				
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<ZipCodeVO> zipList = dao.selectZipcodeList(pagingVO);

		//server-side processing 요구 데이터 스펙
		Map<String, Object> target = new HashMap<>();
		target.put("draw", draw);
		target.put("recordsTotal", totalRecordNonSearch);
		target.put("recordsFiltered", totalRecord);
		target.put("data", zipList);
		
		pagingVO.setData(zipList);
		
		resp.setContentType("application/json;charset=UTF-8");
		
		try(
			PrintWriter out = resp.getWriter();
		){
			ObjectMapper mapper =new ObjectMapper();
			mapper.writeValue(out, target);
		}
		
		
	}
}
