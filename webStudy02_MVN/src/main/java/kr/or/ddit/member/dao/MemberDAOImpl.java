package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {
	
	private static MemberDAOImpl self;
	
	private MemberDAOImpl() {
		super();
	}
	
	public static MemberDAOImpl getInstance() {
		if(self == null) {
			self = new MemberDAOImpl();
		}
		return self;
	}

	@Override
	public int insertMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		                                                                      
		sql.append("INSERT INTO member (                                      ");
		sql.append("	    mem_id,    mem_pass,    mem_name,                 ");
		sql.append("	    mem_regno1,    mem_regno2,    mem_bir,            ");
		sql.append("	    mem_zip,    mem_add1,    mem_add2,                ");
		sql.append("	    mem_hometel,    mem_comtel,    mem_hp,            ");
		sql.append("	    mem_mail,    mem_job,    mem_like,                ");
		sql.append("	    mem_memorial,    mem_memorialday,    mem_mileage, ");
		sql.append("	    mem_delete                                        ");
		sql.append("	) VALUES (                                            ");
		sql.append("	    ?,    ?,    ?,                           		  ");
		sql.append("	    ?,    ?,    to_date( ?, 'YYYY-MM-DD'),            ");
		sql.append("	    ?,    ?,    ?,                              	  ");
		sql.append("	    ?,    ?,    ?,                            		  ");
		sql.append("	    ?,    ?,    ?,                           		  ");
		sql.append("	    ?,    to_date( ?, 'YYYY-MM-DD'),    ?,     		  ");
		sql.append("	    ?)                                 	          	  ");
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_pass());
			pstmt.setString(3, member.getMem_name());
			pstmt.setString(4, member.getMem_regno1());
			pstmt.setString(5, member.getMem_regno2());
			pstmt.setString(6, member.getMem_bir());
			pstmt.setString(7, member.getMem_zip());
			pstmt.setString(8, member.getMem_add1());
			pstmt.setString(9, member.getMem_add2());
			pstmt.setString(10, member.getMem_hometel());
			pstmt.setString(11, member.getMem_comtel());
			pstmt.setString(12, member.getMem_hp());
			pstmt.setString(13, member.getMem_mail());
			pstmt.setString(14, member.getMem_job());
			pstmt.setString(15, member.getMem_like());
			pstmt.setString(16, member.getMem_memorial());
			pstmt.setString(17, member.getMem_memorialday());
			pstmt.setString(18, member.getMem_mileage()+"");
			pstmt.setString(19, member.getMem_delete());
			
			int result = pstmt.executeUpdate();
			
			return result;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		return null;
	}

	@Override
	public MemberVO selectMember(String mem_id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT                                                 ");
	    sql.append(" MEM_ID,    MEM_PASS,    MEM_NAME,                      ");
	    sql.append(" MEM_REGNO1,    MEM_REGNO2,    TO_CHAR(MEM_BIR, 'YYYY-MM-DD') MEM_BIR,");
	    sql.append(" MEM_ZIP,    MEM_ADD1,    MEM_ADD2,                     ");
	    sql.append(" MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,                 ");
	    sql.append(" MEM_MAIL,    MEM_JOB,    MEM_LIKE,                     ");
	    sql.append(" MEM_MEMORIAL,    TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') MEM_MEMORIALDAY,    MEM_MILEAGE,      ");
	    sql.append(" MEM_DELETE                                             ");
	    sql.append(" FROM    MEMBER                                         ");
	    sql.append(" WHERE MEM_ID = ? ");

	    MemberVO savedMember = null;
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, mem_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				 savedMember = MemberVO.builder()
						 .mem_id(rs.getString("mem_id"))
						 .mem_pass(rs.getString("mem_pass"))
						 .mem_name(rs.getString("mem_name"))
						 .mem_name(rs.getString("MEM_REGNO1"))
						 .mem_name(rs.getString("MEM_REGNO2"))
						 .mem_name(rs.getString("MEM_BIR"))
						 .mem_name(rs.getString("MEM_ZIP"))
						 .mem_name(rs.getString("MEM_ADD1"))
						 .mem_name(rs.getString("MEM_ADD2"))
						 .mem_name(rs.getString("MEM_HOMETEL"))
						 .mem_name(rs.getString("MEM_COMTEL"))
						 .mem_name(rs.getString("MEM_HP"))
						 .build();
			}
			return savedMember;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		return 0;
	}

	@Override
	public int deleteMember(String mem_id) {
		return 0;
	}

}
