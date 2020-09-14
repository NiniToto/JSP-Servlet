<%@page import="java.io.FilenameFilter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.File, java.util.Map, java.util.LinkedHashMap, kr.or.ddit.utils.TemplateUtils, java.io.PrintWriter" %>

<%!

	private File folder;
	private String pattern = "<option class = '%s'>%s</option>\n";
	
	private String getImageExpr(String fileMime, String file){
		return String.format(pattern, fileMime, file);
	}
	//private ServletContext application = getServletContext();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script type = "text/javascript" src = "<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

	$(function(){
		
		var imgPtrn = "<img src= '<%= request.getContextPath() %>/image/image.do?image=%s' />";
		var videoPtrn = "<video src = '<%= request.getContextPath() %>/image/image.do?image=%s' />";
		
		var resultArea = $("#resultArea");
		
		$("select").on("change", function(){
			let value = $(this).val();
			let option = $(this).find("option:selected");
			let fileClass = $(option).attr("class");
			
			//console.log("option"+option);
			//console.log("fileClass"+fileClass);
			
			let innerTag = null;
			
			if(fileClass.startsWith("image")){
				innerTag = imgPtrn.replace("%s", value);
			}else if(fileClass.startsWith("video")){
				innerTag = videoPtrn.replace("%s", value);
			}
			
			if(innerTag){
				resultArea.html(innerTag);
			}
		});
	});
	
</script>
<body>
	<select>
	
		<%
		    folder = new File(config.getInitParameter("contentsPath"));
		
			String[] listFiles = folder.list(new FilenameFilter(){

				@Override
				public boolean accept(File dir, String name){
					boolean acceptChk = false;
					
					String mime = application.getMimeType(name);
					acceptChk= mime != null && (mime.startsWith("image/") || mime.startsWith("video/"));
					
					return acceptChk;
				}
			});
			
				out.println("<option>이미지 선택</option>"); //prompt text
				
			for(String file : listFiles) {
				String fileMime = application.getMimeType(file);
				out.println(getImageExpr(fileMime, file));
			}
		%>
		
	</select>
	<div id = "resultArea"></div>
</body>
</html>