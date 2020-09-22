package kr.or.ddit.buyer.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;

public class BuyerDAOImpl implements IBuyerDAO{

	private static IBuyerDAO dao;
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	private BuyerDAOImpl() {
		super();
	}
	
	public static IBuyerDAO getInstance() {
		if(dao == null) {
			dao = new BuyerDAOImpl();
		}
		return dao;
	}
	
	@Override
	public BuyerVO selectBuyer(String buyer_id) {
		try(
			SqlSession session = sqlSessionFactory.openSession();
		){
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyer(buyer_id);
		}
	}
}
