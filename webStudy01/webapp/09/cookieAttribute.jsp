<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/cookieAttribute.jsp</title>
</head>
<body>
<h4> 쿠키의 속성 </h4>
<pre>
	1. 필수 속성(String) : name, value
		RFC - 2396 규약 (% 인코딩, url 인코딩)
		<%--
			String koreanValue = URLEncoder.encode("한글 쿠키", "UTF-8");
			Cookie koreanCookie = new Cookie("koreanCookie", koreanValue);
			response.addCookie(koreanCookie);
		--%>
	2. 부가 속성 : 
		1) expires/max-age : 쿠키의 만료시간 설정, 기본값은 세션과 동일
				0 : 쿠키 삭제(주의! name, value, 그외의 모든 속성이 동일)
				-1 : 브라우저 종료시 삭제됨. (=session)
			<%--
				Cookie longLiveCookie = new Cookie("longLive", "Long~~~");
				longLiveCookie.setMaxAge(-1);
				response.addCookie(longLiveCookie);
			--%>
		2) domain(host) : 다음번 요청 발생 시 서버로 재전송 여부를 결정하는 기준
				domain 구조(level구조), 저수준의 도메인은 고수준의 도메인에 소속됨
				3레벨(GTLD) : ex)www.naver.com
				4레벨(NTLD) : ex)www.naver.co.kr
				ex) .naver.com : naver 도메인의 모든 host를 대상으로 쿠키를 재전송
					mail.naver.com : 네이버의 메일 서버로만 재전송
<!-- 					windows\system32\drivers\etc\hosts 가상 모데인 설정 후 테스트 -->
					session id : <%=session.getId() %>
					<%--
						Cookie allDomainCookie = new Cookie("allDomain", "allDomain~~~");
						allDomainCookie.setDomain("www.ninitoto.com");
						response.addCookie(allDomainCookie);
					--%>
					반드시 도메인은 출처와 레벨구조가 동일해야 한다.
					동일하지 않은 경우 해당 쿠키는 무시된다.
			3) path : 쿠키의 재전송 여부를 결정. 기본값은 쿠키를 생성한 경로
				<%
					Cookie allPath = new Cookie("allPath", "all~~Path~~");
					allPath.setPath(request.getContextPath());
					response.addCookie(allPath);
				%>
				<a href = "<%=request.getContextPath() %>/09/viewCookie.jsp">동일경로에서 쿠키 확인</a>
				<a href = "<%=request.getContextPath() %>/09/inner/viewCookie.jsp">뎁스 구조 상에서 쿠키 확인</a>
				<a href = "<%=request.getContextPath() %>/08/viewCookie.jsp">다른경로에서 쿠키 확인</a>
			4) 기타 : httpOnly, secure(secure protocol을 사용하는 요청에 대해서만 재전송)
				
</pre>
</body>
</html>