package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImpl implements IBuyerDAO{
	
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public BuyerVO selectBuyer(String buyer_id) {
		try(
			SqlSession session = sqlSessionFactory.openSession();
		){		
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyer(buyer_id);
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
			){		
				IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
				return mapper.selectBuyerList(pagingVO);
			}
	}

	@Override
	public int selectBuyerCount(PagingVO<BuyerVO> pagingVO) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
			){		
				IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
				return mapper.selectBuyerCount(pagingVO);
			}
	}

	@Override
	public int insertBuyer(BuyerVO buyerVO) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
			){		
				IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
				return mapper.insertBuyer(buyerVO);
			}
	}

	@Override
	public int updateBuyer(BuyerVO buyerVO) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
			){		
				IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
				return mapper.updateBuyer(buyerVO);
			}
	}

	@Override
	public int deleteBuyer(String buyer_id) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
			){		
				IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
				return mapper.deleteBuyer(buyer_id);
			}
	}
}
