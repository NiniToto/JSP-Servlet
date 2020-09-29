package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface IBuyerService {
	/**
	 * 거래처 상세 조회
	 * @param buyer_id
	 * @return 존재하지 않는 경우 null반환
	 */
	public BuyerVO retrieveBuyer(String buyer_id);
	
	/**
	 * 거래처 목록 조회
	 * @param pagingVO
	 * @return 
	 */
	public List<BuyerVO> retrieveBuyerList(PagingVO<BuyerVO> pagingVO);
	
	/**
	 * 검색조건에 맞는 거래처 수 조회
	 * @param pagingVO
	 * @return
	 */
	public int retrieveBuyerCount(PagingVO<BuyerVO> pagingVO);
	
	/**
	 * 거래처 등록
	 * @param buyerVO
	 * @return PKDUPLICATED, OK, FAILED
	 */
	public ServiceResult registBuyer(BuyerVO buyerVO);
	
	/**
	 * 거래처 정보 수정
	 * @param buyerVO
	 * @return 존재하지 않는 경우, {@link CustomException} 발생 , INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult modifyBuyer(BuyerVO buyerVO);
	
	/**
	 * 거래처 정보 삭제
	 * @param buyer_id
	 * @return 존재하지 않는 경우, {@link CustomException} 발생 , INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult removeBuyer(String buyer_id);
}
