<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript">
		$(function(){
			var imgPtrn = "<img src= 'image.do?image=%s' />";
			var videoPtrn = "<video src = 'image.do?image=%s />'";
			var resultArea = $("#resultArea");
			
			$("select").on("change", function(){
				let value = $(this).val();
				let option = $(this).find("option:selected");
				let fileClass = $(option).attr("class");
				console.log("option"+option);
				console.log("fileClass"+fileClass);
				
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
		<select>
			<option>파일선택</option>
			<%
			
				String[] listFiles = (String[]) request.getAttribute("listFiles");
				for(String file : listFiles){
					String mime = application.getMimeType(file);
					String clz = StringUtils.startsWith(mime, "image/") ? "image"  : StringUtils.startsWith(mime, "video/")? "video" : "none";
					%>
						<option class = "<%=clz%>"><%=file %></option>
					<%
				}
			
			%>
		</select>
		<div id = "resultArea">
			
		</div>
