<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<table id="boardTable" class="table table-bordered">
		<thead>
			<tr>
				<th>게시글번호</th>
				<th>게시글제목</th>
				<th>게시글작성자</th>
				<th>게시글작성일</th>
				<th>게시글조회수</th>
				<th>작성IP</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="datalist" value="${pagingVO.data }" />
			<c:choose>
				<c:when test="${not empty datalist }">
					<c:forEach items="${datalist }" var="board">
						<tr>
							<td>${board.bo_no}</td>
							<td><a href="#" data-what="${board.bo_no}">${board.bo_title}</a></td>
							<td>${board.bo_writer}</td>
							<td>${board.bo_date}</td>
							<td>${board.bo_hit}</td>
							<td>${board.bo_ip}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7">검색 조건에 맞는 게시글이 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7" id="pagingArea">
					${pagingVO.pagingHTML_BS}
				</td>
			</tr>
		</tfoot>
	</table>
<form id="searchForm" action="${pageContext.request.contextPath}/board/boardList.do" class="form-inline">
	<input type="hidden" name="page" />
	<select name='searchType' class="form-control">
		<option value="all">전체</option>
		<option value="title">제목</option>
		<option value="writer">작성자</option>
		<option value="content">내용</option>
	</select>
	<input type="text" class="form-control" name="searchWord" />
	<input type="submit" class="btn btn-primary" value="검색" />
</form>
<div class="modal fade" id="boardViewModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"><span id="whatArea"></span>의 상세정보</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
	let listTable = $("#boardTable");
	let pagingArea = $("#pagingArea");
	let pagingA = pagingArea.on('click', "a" ,function(){
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		searchForm.find("[name='page']").val(1);
		return false;
	});
	let searchForm = $("#searchForm").on("submit", function(event){
		event.preventDefault();
		let url = this.action?this.action:location.href;
		let method = this.method?this.method:"get";
		let data = $(this).serialize(); // query string 
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "json",
			success : function(resp) {
				listTable.find("tbody").empty();
				pagingArea.empty();
				let list = resp.data;
				let trTags = [];
				if(list.length>0){
					$(list).each(function(idx, board){
						trTags.push(
							$("<tr>").append(
								$("<td>").text(board.bo_no),
								$("<td>").html(
										$("<a>").text(board.bo_title)
												.attr("href", "#")
												.data("what", board.bo_no)
								),
								$("<td>").text(board.bo_writer),
								$("<td>").text(board.bo_date),
								$("<td>").text(board.bo_hit),
								$("<td>").text(board.bo_ip)
							)
						);
						
					});
				}else{
					trTags.push($("<tr>").html($("<td colspan='7'>").text("조건에 맞는 게시글이 없음.")));
				}
				listTable.find("tbody").html(trTags);
				pagingArea.html( resp.pagingHTML_BS );
			},
			error : function(errResp) {
			}
		});
		return false;
	});
	$("#boardTable>tbody").on("click","a", function(){
		let what = $(this).data("what"); 
<%-- 		location.href="<%=request.getContextPath() %>/member/memberView.do?who="+who; --%>
		$.ajax({
			url : "${pageContext.request.contextPath}/board/boardView.do",
			method : "get",
			data : {
				what:what
			},
			dataType : "html",
			success : function(resp) {
				$("#boardViewModal").find("#whatArea").text(what);
				$("#boardViewModal").find(".modal-body").html(resp);
				$("#boardViewModal").modal("show");
			},
			error : function(errResp) {
				console.log(errResp);
			}
		});
		return false;
	});
</script>	
</body>
</html>