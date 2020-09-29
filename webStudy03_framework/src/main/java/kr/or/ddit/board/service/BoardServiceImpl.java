package kr.or.ddit.board.service;

import java.io.File;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board.dao.AttatchDAOImpl;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IAttatchDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements IBoardService{

	private IBoardDAO dao;
	private static IBoardService service;
	IAttatchDAO attatchDAO = new AttatchDAOImpl();
	File saveFolder = new File("e:/saveFiles");
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	private BoardServiceImpl() {
		dao = BoardDAOImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(service == null) {
			service = new BoardServiceImpl();
		}
		return service;
	}
	
	@Override
	public int selectBoardCount(PagingVO<BoardVO> pagingVO) {
		return dao.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO) {
		return dao.selectBoardList(pagingVO);
	}
	
	@Override
	public BoardVO selectBoard(int bo_no) {
		BoardVO board = dao.selectBoard(bo_no);
		if(board == null) {
			throw new CustomException(bo_no + "번의 게시글은 존재하지 않습니다.");
		}
		return board;
	}

	@Override
	public ServiceResult createBoard(BoardVO board) {
		
		try(
			//세션 오픈
			SqlSession session = sqlSessionFactory.openSession();
		){
			//1. Board insert
			int rowcnt = dao.createBoard(board); // 1
			// 첨부 파일 존재 여부 확인
			ServiceResult result = ServiceResult.FAILED;
			if(board.getAttatchFiles() != null) {
				result = ServiceResult.OK;
			}
			//2. Attatch insert n번
			rowcnt += attatchDAO.insertAttatchs(board); // 1
			
			//3. binary 저장 n번
			
			
			//세션 커밋
			if(rowcnt > 0) {
				result = ServiceResult.OK;
				session.commit();
			}
			return result;
		}
	}
}
