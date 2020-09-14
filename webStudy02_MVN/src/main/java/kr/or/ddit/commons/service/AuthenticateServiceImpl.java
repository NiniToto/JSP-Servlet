package kr.or.ddit.commons.service;

import kr.or.ddit.commons.dao.AuthenticateDAOImpl;
import kr.or.ddit.commons.dao.IAuthenticateDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements IAuthenticateService {
	
	private static IAuthenticateService service;
	private IAuthenticateDAO dao;
	
	private AuthenticateServiceImpl() {
		dao = AuthenticateDAOImpl.getInstance();
	}
	
	public static IAuthenticateService getInstance() {
		if(service == null) {
			service = new AuthenticateServiceImpl();
		}
		return service;
	}
	
	@Override
	public Object authenticate(MemberVO member) {
		MemberVO savedMember = dao.selectMember(member);
		Object result = null;
		if(savedMember==null) {
			result = ServiceResult.NOTEXIST;
		}else {
			String inputPass = member.getMem_pass();
			String savedPass = savedMember.getMem_pass();
			if(savedPass.equals(inputPass)) {
				result = savedMember;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}
		return result;
	}

}
