package kr.or.ddit.prop.service;

import java.util.Calendar;
import java.util.List;

import kr.or.ddit.prop.dao_JDBC.DataBasePropertyDAO;
import kr.or.ddit.prop.dao_JDBC.IDataBasePropertyDAO;
import kr.or.ddit.prop.service.IDataBasePropertyService;
import kr.or.ddit.vo.PropertyVO;

public class DataBasePropertyServiceImpl implements IDataBasePropertyService {

	//결합력이 최상으로 높다 (HCLC : High Cohession, Loose Coupling)
	IDataBasePropertyDAO dao = new DataBasePropertyDAO();
	
	@Override
	public List<PropertyVO> readDataBaseProperties(PropertyVO param) {
		List<PropertyVO> list = dao.selectDataBaseProperties(param);
		Calendar cal = Calendar.getInstance();
		String ptrn = "%s [%tc]";
		for(PropertyVO vo : list) {
			vo.setProperty_name(String.format(ptrn, vo.getProperty_name(), cal));
		}
		return list;
	}

	@Override
	public List<String> readAllProperty_names() {
		List<String> list = dao.selectAllProperty_names();
		return list;
	}

}
