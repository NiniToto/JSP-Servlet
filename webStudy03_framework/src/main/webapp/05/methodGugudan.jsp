<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	private final String PATTERN = "<td>%d * %d = %d </td>";
	
	private String getGugudanExpr(int dan, int mul){
		return String.format(PATTERN, dan, mul, (dan*mul));
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border = "1">
		<%
		String minDanStr = request.getParameter("minDan");
		String maxDanStr = request.getParameter("maxDan");
		
		int minDan = 2;
		int maxDan = 9;
		
		if(StringUtils.isNumeric(minDanStr)){
			minDan = Integer.parseInt(minDanStr);	
		}
		if(StringUtils.isNumeric(maxDanStr)){
			maxDan = Integer.parseInt(maxDanStr);	
		}
		
		for(int dan = minDan; dan <= maxDan; dan++){
			out.println("<tr>");
			for(int mul = 1; mul <= 9; mul++){
				out.println(getGugudanExpr(dan, mul));		
			}
			out.println("</tr>");
		}
%>

</table>

</body>
</html>