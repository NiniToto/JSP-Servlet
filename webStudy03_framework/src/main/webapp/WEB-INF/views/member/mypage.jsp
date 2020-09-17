<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/mypage.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript">
	$(function(){
		$("#deleteMyData").on("click", function(){
			$("#deleteModal").modal("show");
		});
		
		$("#zipSearchForm").on("submit", function(){

			$("#deleteModal").modal("hide");
			
		})
	});
</script>
</head>
<body>
	<!-- table 태그를 이용하여, 현재 로그인된 유저의 모든 정보를 출력. -->
	<%
		MemberVO authMember = (MemberVO) request.getAttribute("authMember");
	%>
	<table class="table table-bordered">
		<tr>
			<th>아이디</th>
			<td><%=authMember.getMem_id()%></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><%=authMember.getMem_pass()%></td>
		</tr>
		<tr>
			<th>회원명</th>
			<td><%=authMember.getMem_name()%></td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><%=authMember.getMem_regno1()%></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><%=authMember.getMem_regno2()%></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><%=authMember.getMem_bir()%></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><%=authMember.getMem_zip()%></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><%=authMember.getMem_add1()%></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><%=authMember.getMem_add2()%></td>
		</tr>
		<tr>
			<th>집전번</th>
			<td><%=authMember.getMem_hometel()%></td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td><%=authMember.getMem_comtel()%></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><%=authMember.getMem_hp()%></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=authMember.getMem_mail()%></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><%=authMember.getMem_job()%></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><%=authMember.getMem_like()%></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><%=authMember.getMem_memorial()%></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><%=authMember.getMem_memorialday()%></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=authMember.getMem_mileage()%></td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td><%=authMember.getMem_delete()%></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정하기" class="btn btn-primary"
					onclick="location.href='<%=request.getContextPath()%>/myDataUpdate.do';"/>
				<input id = "deleteMyData" class = "btn btn-info" type="button" value="탈퇴" />	
			</td>
		</tr>
	</table>
	
<div class="modal fade" id="deleteModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">                                                                                                                           
    <div class="modal-content">                                                                                                                                 
      <div class="modal-header">                                                                                                                                
        <h5 class="modal-title" id="staticBackdropLabel">회원 탈퇴</h5>                                                                                     
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">                                                                            
          <span aria-hidden="true">&times;</span>                                                                                                               
        </button>                                                                                                                                               
      </div>                                                                                                                                                    
      <div class="modal-body">                                                                                                                                  
	      <form id="zipSearchForm" method="post" class="form-inline" action="<%=request.getContextPath()%>/leaveApp.do">                                  
	       		비밀번호 입력 :  <input type="text" required name="passForDel1" class="form-control" />                                                             
	       		비밀번호 재입력 :  <input type="text" required name="passForDel2" class="form-control" />                                                             
				<button type="submit" class="btn btn-primary" id="completeDelBtn">회원 탈퇴</button>                                                                
	      </form>                                                                                                                                                   
      </div>                                                                                                                                                    
      <div class="modal-footer">                                                                                                                                
        <button id = "closeDelModal" type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>                                                                                                                                                    
    </div>                                                                                                                                                      
  </div>                                                                                                                                                        
</div>  
</body>
</html>










