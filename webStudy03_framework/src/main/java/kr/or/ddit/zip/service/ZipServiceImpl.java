package kr.or.ddit.zip.service;

import java.util.List;

import kr.or.ddit.vo.ZipVO;
import kr.or.ddit.zip.dao.IZipDAO;
import kr.or.ddit.zip.dao.ZipDAOImpl;

public class ZipServiceImpl implements IZipService{

	private IZipDAO dao;
	private static IZipService service;
	
	
	private ZipServiceImpl() {
		dao = ZipDAOImpl.getInstance();
	}
	
	public static IZipService getInstance() {
		if(service == null) {
			service = new ZipServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<ZipVO> zipSearch(String zipInput) {
		return dao.zipSearch(zipInput);
	}
	
}
