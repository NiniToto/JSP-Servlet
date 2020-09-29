package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BoardVO;

public class AttatchDAOImpl implements IAttatchDAO {

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertAttatchs(BoardVO board) {try(
		SqlSession session = sqlSessionFactory.openSession(true);
		){		
		IAttatchDAO mapper = session.getMapper(IAttatchDAO.class);
			return mapper.insertAttatchs(board);
		}
	}
}
