<%@page import="java.io.Console"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	public StringBuffer showTableForMember(MemberVO member){
		StringBuffer table = new StringBuffer();
		table.append("<table>");
		if(member.getMem_id() != null){
			table.append("<tr>");
			table.append("<td>mem_id</td>");
			table.append(String.format("<td>%s</td>", member.getMem_id()));
			table.append("</tr>");
		}else if(member.getMem_name() != null){
			table.append("<tr>mem_name</tr>");
			table.append(String.format("<td>%s</td>", member.getMem_name()));
		}else if(member.getMem_name() != null){
			table.append("<tr>mem_name</tr>");
			table.append(String.format("<td>%s</td>", member.getMem_name()));
		}
		
		return table;
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/mypage.jsp</title>
</head>
<body>
<!-- table 태그를 이용하여, 현재 로그인된 유저의 모든 정보를 출력 -->
<%
	MemberVO member = (MemberVO) request.getAttribute("authMember");
%>
	<table>
		<tr>
			<th>mem_id</th>
			<td><%=member.getMem_id() %></td>
		</tr>
		<tr>
			<th>mem_name</th>
			<td><%=member.getMem_name() %></td>
		</tr>
		<tr>
			<th>mem_pass</th>
			<td><%=member.getMem_pass() %></td>
		</tr>
	</table>
</body>
</html>