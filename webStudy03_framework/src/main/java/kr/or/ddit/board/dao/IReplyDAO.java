package kr.or.ddit.board.dao;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public interface IReplyDAO {
	
	public int selectReplyCount(PagingVO<ReplyVO> pagingVO);
	
	public ReplyVO selectReplyList(PagingVO<ReplyVO> pagingVO);
	
	public int insertReply(ReplyVO replyVO);
	
	public int updateReply(ReplyVO replyVO);
	
	public int deleteReply(int rep_no);

}
