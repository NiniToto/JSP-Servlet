<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! 
private long factorial(int num, StringBuffer expr){
	if(num <= 0){
		throw new IllegalArgumentException("음수는 연산 불가");
	}else if(num == 1){
		expr.append(num);
		return num;
	}else{
		expr.append(num + "* ");
		return num*factorial(num-1, expr);
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String opParam = request.getParameter("op");
	if(StringUtils.isNotBlank(opParam) && StringUtils.isNumeric(opParam)){
		int num = Integer.parseInt(opParam);
		StringBuffer expr = new StringBuffer();
		long result = factorial(num, expr);
%>

<%=String.format("!%d = %s = %d", num, expr, result) %>
<%
try{
	factorial(num, expr);
}catch(IllegalArgumentException e){
	e.printStackTrace();
	%>
		예외는 발생했지만 정상인 것 처럼...
	<%
}

	}
%>
<%
%>
</body>
</html>