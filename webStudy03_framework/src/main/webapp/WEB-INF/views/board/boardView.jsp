<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    


	<table id="boardTable" class="table table-bordered">
		<tr>
			<th>게시글번호</th>
			<td>${board.bo_no}</td>
		</tr>
		<tr>
			<th>게시글 제목</th>
			<td>${board.bo_title}</td>
		</tr>
		<tr>
			<th>게시글 작성자</th>
			<td>${board.bo_writer}</td>
		</tr>
		<tr>
			<th>게시글 작성일</th>
			<td>${board.bo_date }</td>
		</tr>
		<tr>
			<th>게시글 내용</th>
			<td>${board.bo_content}</td>
		</tr>
		<tr>
			<th>게시글 조회수</th>
			<td>${board.bo_hit}</td>
		</tr>
		<tr>
			<th>작성 IP</th>
			<td>${board.bo_ip}</td>
		</tr>
		<tr>
			<th>댓글 목록</th>
			<td>
				<table class="table table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>댓글 작성자</th>
							<th>댓글 내용</th>
							<th>댓글 작성일</th>
							<th>댓글 작성IP</th>
							<th>수정</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:set value="${board.replyList }" var="replyList" />
						
						<c:if test="${not empty replyList }">
							<c:forEach items="${replyList }" var="reply">
								<tr>
									<c:url value="/reply/replyView.do" var="replyURL">
										<c:param name="what" value="${reply.rep_no }" />
									</c:url>
									<td><a href="${replyURL}">${reply.rep_writer}</a></td>
									<td>${reply.rep_content}</td>
									<td>${reply.rep_date}</td>
									<td>${reply.rep_ip}</td>
									<form action="" >
										<td><input type = "submit" class = "modifyReply" value = "수정"></td>
										<td><input type = "submit" class = "deleteReply" value = "삭제"></td>
									</form>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty replyList }">
							<tr>
								<td colspan="4">
									댓글이 존재하지 않습니다.
								</td>
							</tr>
						</c:if>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="7" id="pagingArea">
								${pagingVO.pagingHTML_BS}
							</td>
						</tr>
						<tr>
							<td>
								<input type = "text" name = "rep_writer" placeholder="작성자"/>
							</td>
							<td colspan="7" id = "inputReply">
								<input type = "text" name = "replyVal"/>
								<input type = "submit" value = "댓글 작성" />
								<input type = "reset" value = "취소" />
							</td>
						</tr>
					</tfoot>			
				</table>
			</td>
		</tr>
	</table>
	
<script type = "text/javascript">
	$(function(){
		
		$(document).on("click", ".modifyReply", function(){
			
		});
		
		$(document).on("click", ".deleteReply", function(){
				
		});
	});
</script>
	