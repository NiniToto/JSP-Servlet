package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParameter;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@CommandHandler
public class BoardReadController {

	IBoardService service = BoardServiceImpl.getInstance();
	
	@URIMapping(value="/board/boardView.do", method=HttpMethod.GET)
	public String doGet(@RequestParameter(name="what", required=true) String what, HttpServletRequest req) throws ServletException, IOException {
		
		BoardVO board = service.selectBoard(Integer.parseInt(what));
		req.setAttribute("board", board);
		String goPage = "board/boardView";
		return goPage;
		
	}
	
	@URIMapping(value="/board/boardList.do", method=HttpMethod.GET)
	public String sdfasd(
			@RequestParameter(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParameter(name="searchType", required=false) String searchType,
			@RequestParameter(name="searchWord", required=false) String searchWord,
			HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
//		====검색
		SearchVO searchVO = new SearchVO(searchType, searchWord);
		PagingVO<BoardVO> pagingVO = new PagingVO<>(10, 5);
		pagingVO.setSearchVO(searchVO);
//		========
		int totalRecord = service.selectBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord); // totalPage
		pagingVO.setCurrentPage(currentPage); // startRow, endRow, startPage, endPage
		
		List<BoardVO> boardList = service.selectBoardList(pagingVO);
		pagingVO.setData(boardList);
		
		String accept = req.getHeader("Accept");
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
					PrintWriter out = resp.getWriter();	
					){
				mapper.writeValue(out, pagingVO);
			}
			return null;
		}else {
			req.setAttribute("pagingVO", pagingVO);
			String goPage = "board/boardList";
			return goPage;
		}
	}
}
