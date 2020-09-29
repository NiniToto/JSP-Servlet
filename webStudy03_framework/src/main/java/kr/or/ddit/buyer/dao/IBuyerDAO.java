package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface IBuyerDAO {
	
	/**
	 * 거래처 상세 조회
	 * @param buyer_id
	 * @return 존재하지 않는 경우 null반환
	 */
	public BuyerVO selectBuyer(String buyer_id);
	
	/**
	 * 거래처 목록 조회
	 * @param pagingVO
	 * @return 
	 */
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO);
	
	/**
	 * 검색조건에 맞는 거래처 수 조회
	 * @param pagingVO
	 * @return
	 */
	public int selectBuyerCount(PagingVO<BuyerVO> pagingVO);
	
	/**
	 * 거래처 등록
	 * @param buyerVO
	 * @return 등록 성공 (>0), 실패(==0)
	 */
	public int insertBuyer(BuyerVO buyerVO);
	
	/**
	 * 거래처 정보 수정
	 * @param buyerVO
	 * @return 성공(>0), 실패(==0)
	 */
	public int updateBuyer(BuyerVO buyerVO);
	
	/**
	 * 거래처 정보 삭제
	 * @param buyer_id
	 * @return 성공(>0), 실패(==0)
	 */
	public int deleteBuyer(String buyer_id);
	
}
