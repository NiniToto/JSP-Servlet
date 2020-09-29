<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<script src="${pageContext.request.contextPath }/js/ckeditor/ckeditor.js" />
</head>
<body>
	<textarea name="editor1"></textarea>
    <script>
         CKEDITOR.replace( 'editor1' );
    </script>
</body>
</html>