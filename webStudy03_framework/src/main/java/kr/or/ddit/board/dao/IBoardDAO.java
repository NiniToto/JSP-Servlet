package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardDAO {
	
	public int createBoard(BoardVO board);

	public int selectBoardCount(PagingVO<BoardVO> pagingVO);
	
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	
	public BoardVO selectBoard(int bo_no);
	
	public void incrementHit(int bo_no);
}
