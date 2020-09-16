package kr.or.ddit.zip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.ZipVO;

public class ZipDAOImpl implements IZipDAO{
	
	private static IZipDAO dao;
	
	private ZipDAOImpl() {

	}
	
	public static IZipDAO getInstance() {
		if(dao == null) {
			dao = new ZipDAOImpl();
		}
		return dao;
	}

	@Override
	public List<ZipVO> zipSearch(String zipInput) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append("FROM ZIPTB ");
		sql.append("WHERE SIDO LIKE '%' ||?|| '%' OR GUGUN LIKE '%' ||?|| '%' OR DONG LIKE '%' ||?|| '%' ");
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			List<ZipVO> list = new ArrayList<>(); 
			int idx = 1;
			pstmt.setString(idx++, zipInput);
			pstmt.setString(idx++, zipInput);
			pstmt.setString(idx++, zipInput);
			
			ResultSet result = pstmt.executeQuery();
			
			while(result.next()) {
				list.add(ZipVO.builder()
						.zipcode(result.getString("zipcode"))
						.sido(result.getString("sido"))
						.gugun(result.getString("gugun"))
						.dong(result.getString("dong"))
						.ri(result.getString("ri"))
						.bldg(result.getString("bldg"))
						.bunji(result.getString("bunji"))
						.seq(Integer.parseInt(result.getString("seq")))
						.build());
			}
			return list;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
