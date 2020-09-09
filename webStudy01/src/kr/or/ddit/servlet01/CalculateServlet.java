package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.OperatorType;

@WebServlet("/01/calculate.do")
public class CalculateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String accept = req.getHeader("Accept");
		String mime = "text/html;charset=UTF-8";
		MarchallingType marchallingType = null;
		if(accept.contains("json")) {
			marchallingType = MarchallingType.JSON;
		}else if(accept.contains("xml")) {
			marchallingType = MarchallingType.XML;
		}
		resp.setContentType(marchallingType==null? mime : marchallingType.getMime());
		
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		System.out.println(name);
		
		String param1 = req.getParameter("leftOp");
		String param2 = req.getParameter("rightOp");
		String param3 = req.getParameter("operator");
		
		boolean valid = true;
		if(param1 == null || !param1.matches("-?\\d+")) {
			valid = false;
			System.out.println("param1 : "+valid);
		}
		
		if(param2 == null || !param2.matches("-?[0-9]{1,}")) {
			valid = false;
		}
		
		OperatorType type = null;
		
		if(param3 == null){
		}else {
			try {
				type = OperatorType.valueOf(param3);
			} catch (IllegalArgumentException e) {
				valid = false;
			}
		}
		
		
		Map<String, Object> targerMap = new LinkedHashMap<>();
		
		if(valid) {
			int leftOp = Integer.parseInt(param1);
			int rightOp = Integer.parseInt(param2);
			
			/*
			 * JSON
			 * String result = type.operateToExpression(leftOp, rightOp); 
			 * String jsonStr = String.format("{\"expression\": \"%s\"}", result);
			 * out.println(jsonStr);
			 */
			long result = type.operate(leftOp, rightOp);
			String expression = type.operateToExpression(leftOp, rightOp);
			
			targerMap.put("result", result);
			targerMap.put("expression", expression);

		}else {
			//요청 처리에 실패했음을 전송
			targerMap.put("message", "처리 실패");
		}
		PrintWriter out = resp.getWriter();
		
		String respText = marchallingType.marshalling(targerMap);
		out.println(respText);
		out.close();
	}

	public interface Marshaller{
		public String marshalling(Map<String, Object> targetMap);
	}
	
	public static enum MarchallingType {
		JSON("application/json;charset = UTF-8", new Marshaller() {
			public String marshalling(Map<String,Object> targetMap) {

				StringBuffer jsonText = new StringBuffer("{");
				String pattern = "\"%s\":\"%s\",";
				for(Entry<String, Object> entry : targetMap.entrySet()) {
					jsonText.append(String.format(pattern, entry.getKey(), entry.getValue().toString()));
				}
				int idx  = jsonText.lastIndexOf(",");
				jsonText.deleteCharAt(idx);
				jsonText.append("}");
				return jsonText.toString();
				
			}
		}), 
		XML("application/xml;charset = UTF-8", (targetMap) -> {
			
			StringBuffer xmlText = new StringBuffer("<root>");
			String pattern = "<%1$s>%2$s</%1$s>";
			for(Entry<String, Object> entry : targetMap.entrySet()) {
				xmlText.append(String.format(pattern, entry.getKey(), entry.getValue().toString()));
			}
			xmlText.append("</root>");
			return xmlText.toString();
			
		});
		
		public Marshaller marshaller;
		public String mime;

		private MarchallingType(String mime, Marshaller marshaller) {
			this.mime = mime;
			this.marshaller = marshaller;
		}
		
		public String getMime() {
			return mime;
		}
		
		public String marshalling(Map<String, Object> targetMap) {
			return marshaller.marshalling(targetMap);
		}
	}
}
