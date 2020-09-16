package kr.or.ddit.commons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateDAOImpl implements IAuthenticateDAO {

	@Override
	public MemberVO selectMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT                                                                                        ");
	    sql.append(" MEM_ID,    MEM_PASS,    MEM_NAME,                                                             ");
	    sql.append(" MEM_REGNO1,    MEM_REGNO2,    TO_CHAR(MEM_BIR,'YYYY-MM-DD') MEM_BIR,                           ");
	    sql.append(" MEM_ZIP,    MEM_ADD1,    MEM_ADD2,                                                            ");
	    sql.append(" MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,                                                        ");
	    sql.append(" MEM_MAIL,    MEM_JOB,    MEM_LIKE,                                                            ");
	    sql.append(" MEM_MEMORIAL,    TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') MEM_MEMORIALDAY,    MEM_MILEAGE,      ");
	    sql.append(" MEM_DELETE                                                                                    ");
	    sql.append(" FROM    MEMBER                                                                                ");
	    sql.append(" WHERE MEM_ID = ?                                                                              ");
//	    3333' OR '1' = '1
	    MemberVO savedMember = null;
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, member.getMem_id());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				 savedMember = MemberVO.builder()
						 .mem_id(rs.getString("MEM_ID"))
						 .mem_pass(rs.getString("MEM_PASS"))
						 .mem_name(rs.getString("MEM_NAME"))
						 .mem_regno1(rs.getString("MEM_REGNO1"))
						 .mem_regno2(rs.getString("MEM_REGNO2"))
						 .mem_bir(rs.getString("MEM_BIR"))
						 .mem_zip(rs.getString("MEM_ZIP"))
						 .mem_add1(rs.getString("MEM_ADD1"))
						 .mem_add2(rs.getString("MEM_ADD2"))
						 .mem_hometel(rs.getString("MEM_HOMETEL"))
						 .mem_comtel(rs.getString("MEM_COMTEL"))
						 .mem_hp(rs.getString("MEM_HP"))
						 .mem_mail(rs.getString("MEM_MAIL"))
						 .mem_job(rs.getString("MEM_JOB"))
						 .mem_like(rs.getString("MEM_LIKE"))
						 .mem_memorial(rs.getString("MEM_MEMORIAL"))
						 .mem_memorialday(rs.getString("MEM_MEMORIALDAY"))
						 .mem_mileage(rs.getInt("MEM_MILEAGE"))
						 .mem_delete(rs.getString("MEM_DELETE"))
						 .build();
			}
			return savedMember;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}


















