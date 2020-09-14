package kr.or.ddit.commons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateDAOImpl implements IAuthenticateDAO {
	
	private static IAuthenticateDAO dao;
	
	private AuthenticateDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static IAuthenticateDAO getInstance() {
		if(dao == null) {
			dao = new AuthenticateDAOImpl();
		}
		return dao;
	}

	@Override
	public MemberVO selectMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT                                                 ");
	    sql.append(" MEM_ID,    MEM_PASS,    MEM_NAME,                      ");
	    sql.append(" MEM_REGNO1,    MEM_REGNO2,    MEM_BIR,                 ");
	    sql.append(" MEM_ZIP,    MEM_ADD1,    MEM_ADD2,                     ");
	    sql.append(" MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,                 ");
	    sql.append(" MEM_MAIL,    MEM_JOB,    MEM_LIKE,                     ");
	    sql.append(" MEM_MEMORIAL,    MEM_MEMORIALDAY,    MEM_MILEAGE,      ");
	    sql.append(" MEM_DELETE                                             ");
	    sql.append(" FROM    MEMBER                                         ");
	    sql.append(" WHERE MEM_ID = ? ");

	    MemberVO savedMember = null;
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, member.getMem_id());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				 savedMember = MemberVO.builder()
						 .mem_id(rs.getString("mem_id"))
						 .mem_pass(rs.getString("mem_pass"))
						 .mem_name(rs.getString("mem_name"))
						 .build();
			}
			return savedMember;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}


















