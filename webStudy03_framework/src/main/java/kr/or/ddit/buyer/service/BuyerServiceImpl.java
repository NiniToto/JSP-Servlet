package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerServiceImpl implements IBuyerService{
	
	IBuyerDAO dao = new BuyerDAOImpl();
	
	@Override
	public BuyerVO retrieveBuyer(String buyer_id) {
		BuyerVO buyer = dao.selectBuyer(buyer_id);
		if(buyer == null) {
			throw new CustomException(buyer_id+"에 해당하는 거래처는 없음.");
		}
		return buyer;
	}

	@Override
	public List<BuyerVO> retrieveBuyerList(PagingVO<BuyerVO> pagingVO) {
		return dao.selectBuyerList(pagingVO);
	}

	@Override
	public int retrieveBuyerCount(PagingVO<BuyerVO> pagingVO) {
		return dao.selectBuyerCount(pagingVO);
	}

	@Override
	public ServiceResult registBuyer(BuyerVO buyerVO) {
		ServiceResult result = ServiceResult.FAILED;
		if(dao.selectBuyer(buyerVO.getBuyer_id()) == null) {
			int rowcnt = dao.insertBuyer(buyerVO);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyerVO) {
		ServiceResult result = ServiceResult.FAILED;
		int rowcnt = dao.updateBuyer(buyerVO);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult removeBuyer(String buyer_id) {
		ServiceResult result = ServiceResult.FAILED;
		int rowcnt = dao.deleteBuyer(buyer_id);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}
}