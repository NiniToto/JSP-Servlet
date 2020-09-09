package kr.or.ddit.servlet04_01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.servlet04.ServerExplorerServlet.CommandType;

@WebServlet("/serverExplorer01.do")
public class ServerExplorerServlet01 extends HttpServlet{

	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}

	public static interface FileCommandProcessor{
		public boolean process(File srcFile, File destFolder);
	}
	
	public static enum CommandType{
		COPY((srcFile, destFolder)->{
			try {
				FileUtils.copyFileToDirectory(srcFile, destFolder);
				return true;
			}catch(IOException e) {
				return false;
			}
		}),
		DELETE((srcFile, destFolder)->{
			return FileUtils.deleteQuietly(srcFile);
		}),
		MOVE((srcFile, destFolder)->{
			boolean result = false;
			if(COPY.process(srcFile, destFolder)) {
				result = DELETE.process(srcFile, null);
			}
			return result;
		});
		
		private FileCommandProcessor processor;
		
		private CommandType(FileCommandProcessor processor) {
			this.processor = processor;
		}
		
		public boolean process(File srcFile, File destFolder) {
			return processor.process(srcFile, destFolder);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, Object> errors = new LinkedHashMap<String, Object>();
		boolean valid = validate(req, errors);
		
		if(valid) {
			
			CommandType commandType = (CommandType)  req.getAttribute("commandType");
			File srcFile = (File) req.getAttribute("srcFile");
			File destFolder = (File) req.getAttribute("destFolder");
			
			boolean result = commandType.process(srcFile, destFolder);
			Map<String, Object> resultMap = Collections.singletonMap("sucess", result);
			resp.setContentType("application/json;charset=UTF-8");
			try(
					PrintWriter out = resp.getWriter();
			){
				//marshalling + serialize
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, resultMap);
			}
		}else {
			int sc =  (Integer) errors.get("statusCode");
			String message = (String) errors.get("message");
			resp.sendError(sc, message);
		}
	}
	
	private boolean validate(HttpServletRequest req, Map<String, Object> errors) {
		boolean valid = true;
		
		String cmdParam = req.getParameter("command");
		String srcParam = req.getParameter("srcFile");
		String destParam = req.getParameter("destFolder");
		
		int sc = 200;
		StringBuffer message = new StringBuffer();
		
		CommandType commandType = null;
		
		if(StringUtils.isBlank(cmdParam)) {
			valid = false;
			sc = 400;
			message.append("명령이 전달되지 않았음");
		}else {
			try {
				commandType = CommandType.valueOf(cmdParam);
			}catch(IllegalArgumentException e) {
				valid = false;
				sc = 400;
				message.append("처리할 수 없는 명령임");
			}
		}
		
		req.setAttribute("commandType", commandType);
		
		File srcFile = null;
		if(StringUtils.isBlank(srcParam)) {
			valid = false;
			sc = 400;
			message.append("source 파일 파라미터가 없음");
		}else {
			srcFile = new File(application.getRealPath(srcParam));
			if(!srcFile.exists()) {
				valid = false;
				sc = 400;
				message.append("source 파일이 존재하지 않음");
			}
		}
		req.setAttribute("srcFile", srcFile);
		
		File destFolder = null;
		if(!CommandType.DELETE.equals(commandType) && StringUtils.isBlank(destParam)) {
			valid = false;
			sc= 400;
			message.append("복사나 이동을 위한 대상 폴더 파라미터가 없음");
		}else if(!CommandType.DELETE.equals(commandType)) {
			destFolder = new File(application.getRealPath(destParam));
			if(!destFolder.exists() || destFolder.isFile()) {
				valid = false;
				sc = 400;
				message.append("대상 폴더가 존재하지 않거나, 폴더가 아닌 파일이 대상으로 지정됨");
			}
		}
		req.setAttribute("destFolder", destFolder);
		
		errors.put("statusCode", sc);
		errors.put("message", message.toString());
		return valid;
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파라미터 받기
		String currentURL =  req.getParameter("url");
		String accept = req.getHeader("Accept");
		String currentPath = "/";
		
		//파라미터 검증
		if(StringUtils.isNotBlank(currentURL)) {
			currentPath = currentURL;
		}
		String realPath = application.getRealPath(currentPath);
		File folder = new File(realPath);
		
		int sc = 200;
		if(!folder.exists()) { //경로에 폴더가 없는 경우
			sc = 404;
		}
		if(folder.isFile()) { //폴더가 아닌 파일인 경우
			sc = 400;
		}
		
		if(sc != 200) { //정상적으로 실행되지 않은 경우
			resp.sendError(sc);
			return;
		}
		
		File[] fileList = folder.listFiles();
		FileWrapper01[] wrapper01 = new FileWrapper01[fileList.length];
		
		for (int i = 0; i < wrapper01.length; i++) {
			wrapper01[i] = new FileWrapper01(fileList[i], application);
		}
		
		//json형식일 경우
		if (accept.contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper =  new ObjectMapper();
			//marshalling
			String json = mapper.writeValueAsString(wrapper01);
			try(
					PrintWriter out = resp.getWriter();
			){
				//serialize
				out.println(json);
			}
		}else { //json형식이 아닐경우 forward
			req.setAttribute("fileList", wrapper01);
			req.getRequestDispatcher("/WEB-INF/views/serverExplorer01.jsp").forward(req, resp);
		}
	}
}
