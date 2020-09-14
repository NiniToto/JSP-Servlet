<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.vo.PropertyVO"%>
<%@page import="kr.or.ddit.vo.PropertyVO.PropertyVOBuilder"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript" src = "<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("[name='property_name']").val("${param.property_name}");
		$("formaaaa:first").on("submit", function(event){
			event.preventDefault();
			let url = this.action?this.action:"";
			let method = $(this).attr("method")?this.method:"get";
			
			let data = $(this).serialize(); //query string 생성
			console.log(data);
			
			$.ajax({
				url : url,
				data : data,
				method : method,
				dataType : "json", //Accept, Content-type
				success : function(resp) {
					let trTags = [];
					if(resp.length>0){
						$(resp).each(function(idx, dbProp){
							let tr = $("<tr>");
							tr.append(
								$("<td>").text(dbProp.property_name),
								$("<td>").text(dbProp.property_value),
								$("<td>").text(dbProp.description)
							);
							trTags.push(tr);
						})
						$("#listArea").html(trTags);
					}else{
						let tr = $("<tr>").attr({
							colspan : "3"
						}).append($("<td>").text("검색 결과가 없음."));
						trTags.push(tr);
					}
					$("#listArea").html(trTags);
				},
				error : function(errResp) {

				}
			});
			
			return false;
		})
		
		$("select").on("change", function(event){
			event.preventDefault();
			console.log($(this).val());
			
			
		})
	})
</script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
	Facade Pattern : Facade 객체를 통해 실객체에 명령을 전달하는 구조.
	JDBC 단계
	
	1. 드라이버를 빌드패스에 추가(pom.xml)
	2. 드라이버 로딩(드라이버 클래스를 로딩)
	3. Connection 생성
	4. 쿼리 객체 생성
		1) Statement
		2) PreparedStatement
		3) CallableStatement
	5. 쿼리 실행
		1) ResultSet executeQuery(sql)
		2) int executeUpdate(sql)
	6. 결과 집합 핸들링
	7. 자원을 release
	<%
		List<PropertyVO> list = (List) request.getAttribute("propList");
		PropertyVO paramVO = (PropertyVO) request.getAttribute("param");
		List<String> nameList =  (List) request.getAttribute("nameList");
	%>
</pre>
<form action="<%=request.getContextPath() %>/10/jdbcDesc.do" >
	프로퍼티명 : 
	<select name = "property_name">
		<option value>프로퍼티명 선택</option>
		<%
			for(String name : nameList){
				%>
					<option><%=name %></option>
				<%
			}
		%>
	</select>
	프로퍼티값 : <input type = "text" name = "property_value" value = "${param.property_value} "/>
	디스크립션 : <input type = "text" name = "description" value = "${param.description} "/>
	<input id ="searchBtn" type = "submit" value = "검색" /> 
</form>
<table>
	<thead>
		<tr>
			<th>PROPERTY_NAME?</th>
			<th>PROPERTY_VALUE?</th>
			<th>DESCRIPTION?</th>
		</tr>
	</thead>
	<tbody id = "listArea">
		<%
			for(PropertyVO proVO : list){
				%>
				<tr>
					<td><%=proVO.getProperty_name() %></td>
					<td><%=proVO.getProperty_value() %></td>
					<td><%=proVO.getDescription() %></td>
				</tr>
				<%
			}
		%>
	</tbody>
</table>
</body>
</html>