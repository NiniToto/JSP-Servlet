package kr.or.ddit.zip.service;

import java.util.List;

import kr.or.ddit.vo.ZipVO;

public interface IZipService {

	public List<ZipVO> zipSearch(String zipInput);
	
}
