<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>웰컴 페이지(<%=session.getId() %>)</h4>
<c:choose>
	<c:when test="${not empty authMember }">
		<c:url value="/login/logout.do" var="logoutURL"></c:url>
		<form name="logoutForm" action="${logoutURL }" method="post"></form>
		<c:url value="/mypage.do" var="mypageURL"></c:url>
		현재 로그인 유저 : <a href="${mypageURL }">${authMember.mem_name}</a>
		<a href="#" onclick="document.logoutForm.submit();">로그아웃</a>
	</c:when>
	<c:otherwise>
		<c:url value="/login/loginForm.jsp" var="loginURL" />
		<a href="${loginURL }">로그인하기</a>
		<c:url value="/registMember.do" var="registURL" />
		<a href="${registURL }">회원가입</a>
	</c:otherwise>
</c:choose>
</body>
</html>














