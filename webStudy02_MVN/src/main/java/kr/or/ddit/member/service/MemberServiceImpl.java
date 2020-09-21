package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.commons.service.AuthenticateServiceImpl;
import kr.or.ddit.commons.service.IAuthenticateService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{

	private IMemberDAO dao;
	private IAuthenticateService authenticateService;
	
	private static MemberServiceImpl self;
	
	private MemberServiceImpl() {
		dao = MemberDAOImpl.getInstance();
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
		ServiceResult result = null;
		if(dao.selectMember(member.getMem_id()) == null) {
			int chk = dao.insertMember(member);
			if(chk > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList() {
		return null;
	}

	@Override
	public MemberVO retrieveMember(String mem_id) {
		MemberVO member = new MemberVO();
		
		member = dao.selectMember(mem_id);
		
		if(member == null) {
			throw new CustomException(mem_id + "는 존재하지 않는 회원임");
		}
		
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		return null;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		return null;
	}

	
	
}
