package kr.or.ddit.zip.dao;

import java.util.List;

import kr.or.ddit.vo.ZipVO;

public interface IZipDAO {

	public List<ZipVO> zipSearch(String zipInput);
	
}
