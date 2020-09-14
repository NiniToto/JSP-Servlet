<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
		String minDanStr = request.getParameter("minDan");
		int minDan = Integer.parseInt(minDanStr);
		String maxDanStr = request.getParameter("maxDan");
		int maxDan = Integer.parseInt(maxDanStr);
		%>
		<table border = "1">
		<%
		for(int i = minDan; i <= maxDan; i++){
			%>
			<tr>
			<%
			for(int j = 1; j <= 9; j++){
				%>
				<td>
					<%=String.format("%d * %d = %d", i, j, i*j) %>
				</td>				
				<%
			}
			%>
			</tr>
			<%
		}
		%>
		</table>

</body>
</html>