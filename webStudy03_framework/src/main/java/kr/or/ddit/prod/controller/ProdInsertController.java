package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;
import kr.or.ddit.filter.wrapper.PartWrapper;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelData;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class ProdInsertController{
	IProdService service = new ProdServiceImpl();
	IOthersDAO othersDAO = new OthersDAOImpl();
	
	public void addAttribute(HttpServletRequest req){
		req.setAttribute("lprodList", othersDAO.selectLprodGuList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList());
	}
	
	@URIMapping("/prod/prodInsert.do")
	public String doGet(HttpServletRequest req) throws ServletException, IOException {
		addAttribute(req);
		return "prod/prodForm";
	}
	
	@URIMapping(value="/prod/prodInsert.do", method=HttpMethod.POST)
	public String doPost(@ModelData(name="prod")ProdVO prod, HttpServletRequest req) throws ServletException, IOException {
		addAttribute(req);
		// 검증 전에 prod_img 결정
		if(req instanceof FileUploadRequestWrapper) {

			PartWrapper imageFile = ((FileUploadRequestWrapper) req).getPartWrapper("prod_image");
			if(imageFile!=null) {
				prod.setProd_image(imageFile);
//				imageFile.saveToRealPath(folder);
			}
		}
		
//		2. 검증(DB 스키마 구조 참고)
		Map<String, StringBuffer> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		CommonValidator<ProdVO> validator = new CommonValidator<>();
		boolean valid = validator.validate(prod, errors, InsertGroup.class);
		
		String goPage = null;
		String message = null;
		if(valid) {
//		3. 통과
//		4. 통과한 경우, 로직을 이용한 등록
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case FAILED:
				goPage = "prod/prodForm";
				message = "서버 문제로 등록이 완료되지 않았습니다 잠시 후 다시 시도해주세요.";
				break;

			default:
//				PostRedirectGet pattern
				goPage = "redirect:/prod/prodView.do?what="+prod.getProd_id();
				break;
			}
		}else {
//		   불통
			goPage = "prod/prodForm";
		}
//		  등록 이후의 경우의 수에 대한 처리
		req.setAttribute("message", message);
		return goPage;				
	}
}



