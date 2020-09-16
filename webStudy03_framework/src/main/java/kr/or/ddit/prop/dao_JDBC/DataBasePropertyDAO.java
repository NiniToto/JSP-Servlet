package kr.or.ddit.prop.dao_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.PropertyVO;

public class DataBasePropertyDAO implements IDataBasePropertyDAO{
	

	@Override
	public List<PropertyVO> selectDataBaseProperties(PropertyVO param) {
		
		String sql ="SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION FROM DATABASE_PROPERTIES " ;
		StringBuffer where = new StringBuffer();
		if(param != null) {
			
			String ptrn = " %s LIKE '%%%s%%' ";
			if(StringUtils.isNotBlank(param.getProperty_name())) {
				where.append(String.format(ptrn, "PROPERTY_NAME", param.getProperty_name()));
			}
			if(StringUtils.isNotBlank(param.getProperty_value())) {
				if(where.length() >0) {
					where.append("AND");
				}
				where.append(String.format(ptrn, "PROPERTY_VALUE", param.getProperty_value()));
			}
			if(StringUtils.isNotBlank(param.getDescription())) {
				if(where.length() >0) {
					where.append("AND");
				}
				where.append(String.format(ptrn, "DESCRIPTION", param.getDescription()));
			}
			if(where.length() > 0) {
				where.insert(0, "WHERE");
			}
		}
		sql = sql + where;
		
		List<PropertyVO> list = new ArrayList<>();
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		){
			
			ResultSet rs = stmt.executeQuery(sql.toString());
			
			while(rs.next()){
				
				String property_name = rs.getString("PROPERTY_NAME");
				String property_value = rs.getString("PROPERTY_VALUE");
				String description = rs.getString("DESCRIPTION");
				
				list.add(PropertyVO.getBuilder().property_name(property_name).property_value(property_value).description(description).build());
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> selectAllProperty_names() {
		
		List<String> list = new ArrayList<>();
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		){
			String sql ="SELECT PROPERTY_NAME FROM DATABASE_PROPERTIES " ;
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				String property_name = rs.getString("PROPERTY_NAME");
				
				list.add(property_name);
			}
			
			return list;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
