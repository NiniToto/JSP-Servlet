package kr.or.ddit.prop.dao_JDBC;

import java.util.List;

import kr.or.ddit.vo.PropertyVO;

public interface IDataBasePropertyDAO {

	public List<PropertyVO> selectDataBaseProperties(PropertyVO param);
	
	public List<String> selectAllProperty_names();
	
}
