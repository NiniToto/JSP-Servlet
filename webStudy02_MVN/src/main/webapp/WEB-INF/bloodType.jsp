<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src  = "<%= request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var showArea= $("#showArea");
		$("select:first").on("change", function(){
			let value = $(this).find("option:selected").val();
			//console.log(value);
			
			$.ajax({
				url : "<%= request.getContextPath()%>/getCharacter.do",
				data : {blood : value},
				method : "get",
				dataType : "html",
				success : function(resp) {
					showArea.html(resp);
				},
				error : function(errResp) {
					console.log(errResp);
				}
			});
		});
	});
</script>
</head>
<body>
<pre>
*** 페이지 흐름 제어
혈액형별 성격 데이터 서비스

1. /webStudy01/bloodType.jsp 에서 혈액형 선택 UI 제공
2. 혈액형 선택시 "/webStudy01/getCharacter.do" 를 이용하여,  
   혈액형별 성격 페이지를 서비스함.
   실제 데이터 페이비 : /webStudy01/WEB-INF/blood/o.jsp[a.jsp, b.jsp, ab.jsp, rhM.jsp]
단, 모든 요청은 비동기로 처리하여, 
UI 페이지 하단에 혈액형별 결과 페이지가 랜더링 되도록 함.

제출처 : https://tinyurl.com/yyndeb5d
</pre>
<hr>
<select>
	<%
		Map<String, String[]> bloodMap = (Map) getServletContext().getAttribute("bloodMap");
		String Pattern = "<option value = %s>%s</option>";
		
		Set<String> keySet =  bloodMap.keySet();
		Iterator<String> it = keySet.iterator();
		
		while(it.hasNext()){
			String key = it.next();
			String[] value = bloodMap.get(key);
			out.println(String.format(Pattern, key, value[0]));
		}
		
	%>
</select>
<div id = "showArea"></div>
</body>
</html>