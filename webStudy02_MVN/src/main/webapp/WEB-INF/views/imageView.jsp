<%@page import="java.net.URLDecoder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String jsonImageNames =  (String) request.getAttribute("imgCookie");
	
%>    
	<script type="text/javascript">
		$(function(){
			var imageRendering = function(option, value){
				let fileClass = $(option).attr("class");
				if(fileClass.startsWith("image")){
					innerTag = imgPtrn.replace("%s", value);
				}else if(fileClass.startsWith("video")){
					innerTag = videoPtrn.replace("%s", value);
				}
				if(innerTag){
					resultArea.append(innerTag);
				}
			}
			var imgPtrn = "<img src= 'image.do?image=%s' />";
			var videoPtrn = "<video src = 'image.do?image=%s />'";
			var resultArea = $("#resultArea");
			
			let innerTag = null;
			
			var  select = $("select").on("change", function(){
// 				let array = $(this).val();
// 				console.log(array);
				let options = $(this).find("option:selected");
				console.log(options);
				resultArea.empty();
				
				$(options).each(function(idx, option){
					let value = $(this).text();
					setTimeout(function(){
						imageRendering(option, value);
					}, 1000);
				});
				//console.log("option"+option);
				//console.log("fileClass"+fileClass);
			});
			
			<%
				if(StringUtils.isNotBlank(jsonImageNames)){
					%>
						let json = '<%=jsonImageNames%>';
						let objs = JSON.parse(json);
						console.log(objs);
						select.val(objs);
						select.trigger("change");
					<%
				}
			%>
		});
	</script>
		<select multiple size = "10">
			<option>파일선택</option>
			<%
				String[] listFiles = (String[]) request.getAttribute("listFiles");
				for(String file : listFiles){
					String mime = application.getMimeType(file);
					String clz = StringUtils.startsWith(mime, "image/") ? "image"  : StringUtils.startsWith(mime, "video/")? "video" : "none";
					%>
						<option class = "<%=clz%>" ><%=file %></option>
					<%
				}
			%>
		</select>
		<div id = "resultArea">
		</div>
