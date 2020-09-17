package kr.or.ddit.commons.dao;

import java.util.List;

import kr.or.ddit.vo.ZipCodeVO;

public interface IZipCodeSearchDAO {
	public List<ZipCodeVO> selectZipcodeList(String keyword);
}
