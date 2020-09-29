package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardDAOImpl implements IBoardDAO{

	private static IBoardDAO dao;
	
	private BoardDAOImpl() {
		super();
	}
	
	public static IBoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAOImpl();
		}
		return dao;
	}
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int selectBoardCount(PagingVO<BoardVO> pagingVO) {
		try(
			SqlSession session = sqlSessionFactory.openSession(true);
		){		
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.selectBoardCount(pagingVO);
		}
	}

	@Override
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO) {
		try(
				SqlSession session = sqlSessionFactory.openSession(true);
			){		
				IBoardDAO mapper = session.getMapper(IBoardDAO.class);
				return mapper.selectBoardList(pagingVO);
			}
	}
	
	@Override
	public BoardVO selectBoard(int bo_no) {
		try(
			SqlSession session = sqlSessionFactory.openSession(true);
		){		
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.selectBoard(bo_no);
		}
	}

	@Override
	public void incrementHit(int bo_no) {
		try(
			SqlSession session = sqlSessionFactory.openSession(true);
		){		
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			//return mapper.incrementHit(bo_no);
		}
	}

	@Override
	public int createBoard(BoardVO board) {
		try(
			SqlSession session = sqlSessionFactory.openSession(true);
		){		
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.createBoard(board);
		}
	}
}
