<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript">
	$(function(){
		var registForm = $("#registForm");
		var inInputtag = $("[name = mem_id]");
		$("input").addClass("form-control");
		inInputtag.on("change", function(){
			registForm.data("validId", false);
			$(this).next(".idMsg").remove();
		});
		
		registForm.on("submit", function(){
			
			let valid = $(this).data("validId");
			if(!valid){
				alert("아이디 중복 체크 하세요!");
				valid = false;
			}
			return valid;
		});
		
		$("#checkBtn").on("click", function(event){
			let memId = inInputtag.val();
			let data = {};
			data.memId = memId;
			console.log(data);
			$.ajax({
				url : "<%=request.getContextPath()%>/idCheck.do",
				//inputId 파라미터로 중복 확인
				data : data,
				method : "post",
				dataType : "json", //Accpt(request), Content-Type(response)
				success : function(resp) {
					if(resp.valid){
						let msgTag = inInputtag.next(".idMsg");
						if(msgTag.length == 0){
							inInputtag.after("<span class = 'idMsg'>아이디 사용가능</span>");
						}
						registForm.data("validId", true);
					}else{
						alert("아이디 중복");
						inInputtag.focus();
					}
				},
				error : function(errResp) {
					
				}
			});
		});
	<%
		String message = (String) request.getAttribute("message");
		if(StringUtils.isNotBlank(message)){
			%>
				alert("<%=message%>");
			<%
		}
	%>
	});
</script>
</head>

<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"></jsp:useBean>
<jsp:useBean id="errors" class = "java.util.LinkedHashMap" scope="request"></jsp:useBean>
<body>
	<form id = "registForm" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="TEXT" required name="mem_id" value = "${member.mem_id}" />
					<button type = "button" id = "checkBtn" >아이디중복체크</button>
					<span class = "error"><%=errors.get("mem_id") %></span>				
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="TEXT" name="mem_pass" value = "<%=member.getMem_pass()%>" /><span class = "error"><%=errors.get("mem_pass")%></span></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="TEXT" name="mem_name" value = "<%=member.getMem_name()%>" /><span class = "error"><%=errors.get("mem_name")%></span></td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="TEXT" name="mem_regno1" value = "<%=member.getMem_regno1()%>" /><span class = "error"><%=errors.get("mem_regno1")%></span></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="TEXT" name="mem_regno2" value = "<%=member.getMem_regno2()%>" /><span class = "error"><%=errors.get("mem_regno2")%></span></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" name="mem_bir" value = "<%=member.getMem_bir()%>" placeholder = "1991-01-01" pattern = "yy-MM-dd"/><span class = "error"><%=errors.get("mem_bir")%></span></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="TEXT" name="mem_zip" value = "<%=member.getMem_zip()%>" /><span class = "error"><%=errors.get("mem_zip")%></span></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="TEXT" name="mem_add1" value = "<%=member.getMem_add1()%>" /><span class = "error"><%=errors.get("mem_add1")%></span></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="TEXT" name="mem_add2" value = "<%=member.getMem_add2()%>" /><span class = "error"><%=errors.get("mem_add2")%></span></td>
			</tr>
			<tr>
				<th>집번호</th>
				<td><input type="TEXT" name="mem_hometel" value = "<%=member.getMem_hometel()%>" /><span class = "error"><%=errors.get("mem_hometel")%></span></td>
			</tr>
			<tr>
				<th>회사번호</th>
				<td><input type="TEXT" name="mem_comtel" value = "<%=member.getMem_comtel()%>" /><span class = "error"><%=errors.get("mem_comtel")%></span></td>
			</tr>
			<tr>
				<th>휴대번호</th>
				<td><input type="TEXT" name="mem_hp" value = "<%=member.getMem_hp()%>" /><span class = "error"><%=errors.get("mem_hp")%></span></td>
			</tr>
			<tr>
				<th>메일</th>
				<td><input type="TEXT" name="mem_mail" value = "<%=member.getMem_mail()%>" /><span class = "error"><%=errors.get("mem_mail")%></span></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="TEXT" name="mem_job" value = "<%=member.getMem_job()%>" /><span class = "error"><%=errors.get("mem_job")%></span></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="TEXT" name="mem_like" value = "<%=member.getMem_like()%>" /><span class = "error"><%=errors.get("mem_like")%></span></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="TEXT" name="mem_memorial" value = "<%=member.getMem_memorial()%>" /><span class = "error"><%=errors.get("mem_memorial")%></span></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="TEXT" name="mem_memorialday" value = "<%=member.getMem_memorialday()%>" /><span class = "error"><%=errors.get("mem_memorialday")%></span></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="number" name="mem_mileage" value = "<%=member.getMem_mileage()%>" /><span class = "error"><%=errors.get("mem_mileage")%></span></td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td><input type="TEXT" name="mem_delete" value = "<%=member.getMem_delete()%>" /><span class = "error"><%=errors.get("mem_delete")%></span></td>
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