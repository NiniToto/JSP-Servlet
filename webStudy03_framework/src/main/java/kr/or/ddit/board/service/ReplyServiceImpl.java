package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.IReplyDAO;
import kr.or.ddit.board.dao.ReplyDAOImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public class ReplyServiceImpl implements IReplyService{

	private static IReplyService service;
	private IReplyDAO dao;
	
	private ReplyServiceImpl() {
		dao = ReplyDAOImpl.getInstance();
	}
	
	public static IReplyService getInstance() {
		if(service == null) {
			service = new ReplyServiceImpl();
		}
		return service;
	}
	
	@Override
	public int selectReplyCount(PagingVO<ReplyVO> pagingVO) {
		return dao.selectReplyCount(pagingVO);
	}

	@Override
	public ReplyVO selectReplyList(PagingVO<ReplyVO> pagingVO) {
		return dao.selectReplyList(pagingVO);
	}

	@Override
	public ServiceResult insertReply(ReplyVO replyVO) {
		ServiceResult result = null;
		
		return null;
	}

	@Override
	public ServiceResult updateReply(ReplyVO replyVO) {

		return null;
	}

	@Override
	public ServiceResult deleteReply(int rep_no) {

		return null;
	}
}
