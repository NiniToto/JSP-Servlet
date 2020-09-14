package kr.or.ddit.prop.service;

import java.util.List;

import kr.or.ddit.vo.PropertyVO;

public interface IDataBasePropertyService {
	
	/**
	 * property 정보를 읽고, property_name에 시간 데이터를 concat.
	 * Business Logic Layer(Service Layer)
	 * @param param TODO
	 * @return
	 */
	public List<PropertyVO> readDataBaseProperties(PropertyVO param);
	
	public List<String> readAllProperty_names();
}
