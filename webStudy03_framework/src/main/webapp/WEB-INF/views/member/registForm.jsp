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
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<link rel = "stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css"></link>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	$(function() {
		var registForm = $("#registForm");
		var inInputTag = $("[name='mem_id']");
		$("input").addClass("form-control");
		inInputTag.on("change", function(){
			registForm.data("validId", false);
			$(this).next('.idMsg').remove();
		});
		registForm.on("submit", function(){
			let valid = $(this).data("validId");
			if(!valid){
				alert("아이디 중복 체크 하세요!");
				valid=false;
			}
			return valid;
		});
		
		$("#checkBtn").on("click", function(){
			let inputId = inInputTag.val();
			$.ajax({
				url : "<%=request.getContextPath()%>/idCheck.do",
				// inputId 파라미터로 중복 확인
				data : {
					inputId:inputId
				},
				method : "post",
				dataType : "json", // Accept(request), Content-Type(response)
				success : function(resp) {
					if(resp.valid){
						let msgTag = inInputTag.next(".idMsg");
						if(msgTag.length==0){
							inInputTag.after("<span class='idMsg'>아이디 사용가능</span>");
						}
						registForm.data("validId", true);
					}else{
						alert("아이디 중복, 바꾸셈.");
						$("[name='mem_id']").focus();
					}
				},
				error : function(errResp) {
					console.log(errResp);
				}
			});
		});
		
		$("#zipSearchBtn").on("click", function(){
			$("#msgArea").modal("show");
		});
		
		$("#yesBtn").on("click", function(){
			let zipInput = $("#zipInput").val();
			//console.log(zipInput);
			
			let data = {};
			data.zipInput = zipInput;
			
			$.ajax({
				url : "<%=request.getContextPath()%>/zipSearch.do",
				data : data,
				method : "post",
				dataType : "json",
				success : function(resp) {
					
					let table = $("<table>").addClass("ziptable");
					let thead = $("<thead>").addClass("ziptable thead");
					
					let zipHead = $("<th>").addClass("thead head-cont").text("우편번호");
					let sidoHead = $("<th>").addClass("thead head-cont").text("시도");
					let gugunHead = $("<th>").addClass("thead head-cont").text("구군");
					let dongHead = $("<th>").addClass("thead head-cont").text("동");
					let headTag = $("<tr>").append(zipHead, sidoHead, gugunHead, dongHead);
					
					thead.append(headTag);
					table.append(thead);
					
					$.each(resp, function(idx, property){
						let ziptr = $("<tr>").addClass("ziptr");
						let ziptd = $("<td>").addClass("ziptd").text(property.zipcode);
						let sidotd = $("<td>").addClass("sidotd").text(property.sido);
						let guguntd = $("<td>").addClass("guguntd").text(property.gugun);
						let dongtd = $("<td>").addClass("dongtd").text(property.dong);
						ziptr.append(ziptd, sidotd, guguntd, dongtd);
						
						table.append(ziptr);
					});
					
					$("#zipInput").after(table);
					$(table).DataTable( {
				        "pagingType": "full_numbers"
				    } );
					
				},
				error : function(errResp) {
					console.log(errResp);
				}
			});
		});
		
		$(document).on("click", ".ziptr", function(){
			let zipVal = $(this).find(".ziptd").text();
			let sidoVal = $(this).find(".sidotd").text();
			let gugunVal = $(this).find(".guguntd").text();
			let dongVal = $(this).find(".dongtd").text();
			
			$("[name = mem_zip]").val(zipVal);
			$("[name = mem_add1]").val(sidoVal+ " " +gugunVal+ " " + dongVal);
			
			$("#msgArea").modal("hide");
		});
		
		$(document).on("click", ".selectZip", function(){
			
		})
		
		$("#noBtn").on("click", function(){
			$("#msgArea").modal("hide");
		});
	
	<%
	String message = (String) request.getAttribute("message");
			if (StringUtils.isNotBlank(message)) {
	%>
	    	//alert("<%=message%>);
	");
<%}%>
	});
</script>
<style>
	input{
		width: 200px;
	}
	.head-cont{
		padding : 3px;
	}
</style>
</head>
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request" />
<jsp:useBean id="errors" class="java.util.LinkedHashMap" scope="request" />
<body>
	<form class="cmxform" id="registForm" method="post" action="">
		<table class="table table-bordered">
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" required name="mem_id"
						value="<%=member.getMem_id()%>" maxLength="15" />
					<button type="button" id="checkBtn">아이디중복체크</button>	
					<span class="error"><%=errors.get("mem_id") %></span>	
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" required name="mem_pass" value="<%=member.getMem_pass()%>" maxLength="15" />
			<span class='error'><%=errors.get("mem_pass") %></span></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="text" required name="mem_name" value="<%=member.getMem_name()%>" maxLength="20" />
			<span class='error'><%=errors.get("mem_name") %></span></td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" required name="mem_regno1" value="<%=member.getMem_regno1()%>" maxLength="6" />
			<span class='error'><%=errors.get("mem_regno1") %></span></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" required name="mem_regno2" value="<%=member.getMem_regno2()%>" maxLength="7" />
			<span class='error'><%=errors.get("mem_regno2") %></span></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="text" name="mem_bir" value="<%=member.getMem_bir()%>" maxLength="7" />
			<span class='error'><%=errors.get("mem_bir") %></span></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" required name="mem_zip" value="<%=member.getMem_zip()%>" maxLength="7" />
				<input id = "zipSearchBtn" type = "button" class = "btn btn-info" value = "우편번호 검색" width="100"/>
				<span class='error'><%=errors.get("mem_zip") %></span>
			</td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" required name="mem_add1" value="<%=member.getMem_add1()%>" maxLength="100" />
			<span class='error'><%=errors.get("mem_add1") %></span></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" required name="mem_add2" value="<%=member.getMem_add2()%>" maxLength="80" />
			<span class='error'><%=errors.get("mem_add2") %></span></td>
			</tr>
			<tr>
				<th>집전번</th>
				<td><input type="text" required name="mem_hometel" value="<%=member.getMem_hometel()%>" maxLength="14" />
			<span class='error'><%=errors.get("mem_hometel") %></span></td>
			</tr>
			<tr>
				<th>회사전번</th>
				<td><input type="text" required name="mem_comtel" value="<%=member.getMem_comtel()%>" maxLength="14" />
			<span class='error'><%=errors.get("mem_comtel") %></span></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="text" name="mem_hp" value="<%=member.getMem_hp()%>" maxLength="15" />
			<span class='error'><%=errors.get("mem_hp") %></span></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" required name="mem_mail" value="<%=member.getMem_mail()%>" maxLength="40" />
			<span class='error'><%=errors.get("mem_mail") %></span></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="mem_job" value="<%=member.getMem_job()%>" maxLength="40" />
			<span class='error'><%=errors.get("mem_job") %></span></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="mem_like" value="<%=member.getMem_like()%>" maxLength="40" />
			<span class='error'><%=errors.get("mem_like") %></span></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" name="mem_memorial" value="<%=member.getMem_memorial()%>" maxLength="40" />
			<span class='error'><%=errors.get("mem_memorial") %></span></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="text" name="mem_memorialday" value="<%=member.getMem_memorialday()%>" maxLength="7" />
			<span class='error'><%=errors.get("mem_memorialday") %></span></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="number" name="mem_mileage" value="<%=member.getMem_mileage()%>" maxLength="22" />
			<span class='error'><%=errors.get("mem_mileage") %></span></td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td><input type="text" name="mem_delete" value="<%=member.getMem_delete()%>" maxLength="1" />
			<span class='error'><%=errors.get("mem_delete") %></span></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="전송" /> 
					<input type="reset" value="취소" />
				</td>
			</tr>
		</table>
	</form>
	<div class="modal fade" id="msgArea" tabindex="-1">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">주소 검색</h5>
	      </div>
	      <div class="modal-body">
	        <p>검색할 주소를 입력해주세요!</p>
	        <input id = "zipInput" type = "text" placeholder = "주소 입력" />
	      </div>
	      <div class="modal-footer">
	        <button  type="button" class="btn btn-primary controlBtn" id="yesBtn">검색</button>
	        <button type="button" class="btn btn-primary controlBtn" id="noBtn">닫기</button>
	      </div>
	    </div>
	  </div>
	</div>
</body>
</html>













