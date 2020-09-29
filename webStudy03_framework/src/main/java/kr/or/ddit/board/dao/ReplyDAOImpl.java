package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public class ReplyDAOImpl implements IReplyDAO{
	
	private static IReplyDAO dao;
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	private ReplyDAOImpl() {
		super();
	}
	
	public static IReplyDAO getInstance() {
		if(dao == null) {
			dao = new ReplyDAOImpl();
		}
		return dao;
	}
	
	@Override
	public int selectReplyCount(PagingVO<ReplyVO> pagingVO) {
		try(
			SqlSession session = sqlSessionFactory.openSession(true);
		){		
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.selectReplyCount(pagingVO);
		}
	}

	@Override
	public ReplyVO selectReplyList(PagingVO<ReplyVO> pagingVO) {
		try(
			SqlSession session = sqlSessionFactory.openSession(true);
		){		
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.selectReplyList(pagingVO);
		}
	}

	@Override
	public int insertReply(ReplyVO replyVO) {
		try(
			SqlSession session = sqlSessionFactory.openSession(true);
		){		
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.insertReply(replyVO);
		}
	}

	@Override
	public int updateReply(ReplyVO replyVO) {
		try(
			SqlSession session = sqlSessionFactory.openSession(true);
		){		
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.updateReply(replyVO);
		}
	}

	@Override
	public int deleteReply(int rep_no) {
		try(
			SqlSession session = sqlSessionFactory.openSession(true);
		){		
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.deleteReply(rep_no);
		}
	}
}
