package kr.or.ddit.board.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public interface IReplyService {

	public int selectReplyCount(PagingVO<ReplyVO> pagingVO);
	
	public ReplyVO selectReplyList(PagingVO<ReplyVO> pagingVO);
	
	public ServiceResult insertReply(ReplyVO replyVO);
	
	public ServiceResult updateReply(ReplyVO replyVO);
	
	public ServiceResult deleteReply(int rep_no);
	
}
