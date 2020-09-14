package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.CookieUtils.TextType;

// http://localhost/webStudy01/image/image.do
@WebServlet(urlPatterns = "/image/image.do", 
			initParams = { @WebInitParam(name = "contentsPath", value = "d:/contents") })	
public class ImageStreamingServlet extends HttpServlet{
	private File folder;
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println(getServletContext().hashCode());
		application = getServletContext();
//		String contentsPath = config.getInitParameter("contentsPath");
		String contentsPath = application.getInitParameter("contentsPath");
		folder = new File(contentsPath);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imageName = req.getParameter("image");
		
		if(imageName==null || imageName.trim().length()==0) {
			resp.sendError(400);
			return;
		}
		File imageFile = new File(folder, imageName);
		if(!imageFile.exists()) {
			resp.sendError(404);
			return;
		}
		String mime = getServletContext().getMimeType(imageName);
		if(mime==null) {
			mime = "application/octet-stream";
		}
		
		//Cookie[] cookies =  req.getCookies();
		String alreadyImages = null;
		String[] alreadyArray = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		CookieUtils cookieUtils = new CookieUtils(req);
		alreadyImages = cookieUtils.getCookieValue("imgCookie");
		if(StringUtils.isNotBlank(alreadyImages)) {
			alreadyArray = mapper.readValue(alreadyImages, String[].class);
		}
	
		String[] cookieValue = null;
		if(alreadyArray != null) {
			cookieValue = new String[alreadyArray.length+1];
			System.arraycopy(alreadyArray, 0, cookieValue, 0, alreadyArray.length);
			cookieValue[alreadyArray.length] = imageName;
		}else {
			cookieValue = new String[] {imageName};
		}
		String json = mapper.writeValueAsString(cookieValue);
		
		
		Cookie imgCookie = CookieUtils.createCookie("imgCookie", json, TextType.PATH, req.getContextPath(), 3600 * 24 * 2);
		resp.addCookie(imgCookie);
		
//		try with resource 구문 , 1.7 부터 사용.
		resp.setContentType(mime);
		
		try(
			// Closable 가능 객체의 생성 및 할당 코드, try 블럭이 종료될때 자동으로 close됨.
			FileInputStream fis = new FileInputStream(imageFile);
			OutputStream os = resp.getOutputStream();
		){
			// 예외 발생 가능 코드
			byte[] buffer = new byte[1024];
			int pointer = -1;
			while ((pointer = fis.read(buffer))!=-1) {
				os.write(buffer, 0, pointer);
			}
			os.flush();
		}
	}
}













