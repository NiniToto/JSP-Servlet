<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- <script type="text/javascript" src = "<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#registForm").on("submit", function(){
			$(this).serialize();
		})
	})
</script> --%>
</head>
<body>
	<form id = "registForm" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="TEXT" name="mem_id" /></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="TEXT" name="mem_pass" /></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="TEXT" name="mem_name" /></td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="TEXT" name="mem_regno1" /></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="TEXT" name="mem_regno2" /></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="TEXT" name="mem_bir" /></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="TEXT" name="mem_zip" /></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="TEXT" name="mem_add1" /></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="TEXT" name="mem_add2" /></td>
			</tr>
			<tr>
				<th>집번호</th>
				<td><input type="TEXT" name="mem_hometel" /></td>
			</tr>
			<tr>
				<th>회사번호</th>
				<td><input type="TEXT" name="mem_comtel" /></td>
			</tr>
			<tr>
				<th>휴대번호</th>
				<td><input type="TEXT" name="mem_hp" /></td>
			</tr>
			<tr>
				<th>메일</th>
				<td><input type="TEXT" name="mem_mail" /></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="TEXT" name="mem_job" /></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="TEXT" name="mem_like" /></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="TEXT" name="mem_memorial" /></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="TEXT" name="mem_memorialday" /></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="number" name="mem_mileage" /></td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td><input type="TEXT" name="mem_delete" /></td>
			</tr>
			<tr>
				<td colspan = "2">
					<input id = "submitBtn" type = "submit" value = "전송" />
					<input type = "reset" value = "취소" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>