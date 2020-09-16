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
		if(self==null)
			self = new MemberDAOImpl();
		return self;
	}

	@Override
	public int insertMember(MemberVO member) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO MEMBER (													");	
		sql.append("     MEM_ID,    MEM_PASS,    MEM_NAME,    MEM_REGNO1,                   ");
		sql.append("     MEM_REGNO2,    MEM_BIR,    MEM_ZIP,    MEM_ADD1,                   ");
		sql.append("     MEM_ADD2,    MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,                ");
		sql.append("     MEM_MAIL,    MEM_JOB,    MEM_LIKE,    MEM_MEMORIAL,                ");
		sql.append("     MEM_MEMORIALDAY,    MEM_MILEAGE,    MEM_DELETE                     ");
		sql.append(" ) VALUES (                                                             ");
		sql.append(" 	?,    ?,    ?,    ?,                                                ");
		sql.append("     ?,   TO_DATE(?, 'YYYY-MM-DD' ),    ?,    ?,                                               ");
		sql.append("     ?,    ?,    ?,    ?,                                               ");
		sql.append("     ?,    ?,    ?,    ?,                                               ");
		sql.append("     TO_DATE(?, 'YYYY-MM-DD' ),    ?,    ?                                                      ");
		sql.append(" )                                                                      ");
			
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			int idx = 1;
			pstmt.setString(idx++, member.getMem_id());
			pstmt.setString(idx++, member.getMem_pass());
			pstmt.setString(idx++, member.getMem_name());
			pstmt.setString(idx++, member.getMem_regno1());
			pstmt.setString(idx++, member.getMem_regno2());
			pstmt.setString(idx++, member.getMem_bir());
			pstmt.setString(idx++, member.getMem_zip());
			pstmt.setString(idx++, member.getMem_add1());
			pstmt.setString(idx++, member.getMem_add2());
			pstmt.setString(idx++, member.getMem_hometel());
			pstmt.setString(idx++, member.getMem_comtel());
			pstmt.setString(idx++, member.getMem_hp());
			pstmt.setString(idx++, member.getMem_mail());
			pstmt.setString(idx++, member.getMem_job());
			pstmt.setString(idx++, member.getMem_like());
			pstmt.setString(idx++, member.getMem_memorial());
			pstmt.setString(idx++, member.getMem_memorialday());
			pstmt.setInt(idx++, member.getMem_mileage());
			pstmt.setString(idx++, member.getMem_delete());
			
			return pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO selectMember(String mem_id) {
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
			pstmt.setString(1, mem_id);
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

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String mem_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
