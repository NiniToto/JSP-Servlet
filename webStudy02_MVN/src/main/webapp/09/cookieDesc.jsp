<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> Cookie </h4>
<pre>

	: Http(Stateless) 특성의 단점을 보완하기 위한 방법
	1. session : 상태정보를 서버측에 저장하는 방법
		1) 서버 부하
		2) 데이터의 저장 기간이 제한된다.(timeout이내)
		
	2. cookie : 상태정보를 클라이언트측에 저장하는 방법
		1) 보안 취약
		2) 네트워크 부하를 줄이기 위해 크기가 제한된다.
	
	<%--
		Cookie sampleCookie = new Cookie("sampleCookie", "sampleValue");
		response.addCookie(sampleCookie);
	--%>
	쿠키 사용 방법
	1. 서버측에서 쿠키 (객체) 생성 - name, value
	2. 쿠키의 속성 설정
	3. 응답데이터에 쿠키를 포함시켜 전송
	
	4. 쿠키를 자기 저장소에 저장
	5. 다음번 요청에 쿠키를 재전송 - 필수를 제외한 나머지 속성들에 대한 정보를 포함
	
	6. 재전송된 쿠키를 받고, 상태 복원
	<%
		Cookie[] cookies= request.getCookies();
		String cookieValue = null;
		
		if(cookies != null){
			for(Cookie tmp : cookies){
				if("sampleCookie".equals(tmp.getName())){
					cookieValue = tmp.getValue();
					break;
				}
		}
			
		}
	%>
	<%=cookieValue %>
</pre>
</body>
</html>