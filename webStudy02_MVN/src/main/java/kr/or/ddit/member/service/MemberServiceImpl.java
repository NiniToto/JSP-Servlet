package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.commons.service.AuthenticateServiceImpl;
import kr.or.ddit.commons.service.IAuthenticateService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{

	private IMemberDAO dao = MemberDAOImpl.getInstance();
	private IAuthenticateService authenticateService;
	
	private static MemberServiceImpl self;
	
	private MemberServiceImpl() {
		authenticateService = AuthenticateServiceImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if(self == null) {
			self = new MemberServiceImpl();
		}
		return self;
	}
	
	@Override
	public ServiceResult registMember(MemberVO member) {
		int chk = dao.insertMember(member);
		ServiceResult result;
		if(chk > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO retrieveMember(String mem_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
