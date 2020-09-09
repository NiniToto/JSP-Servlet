<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	public boolean isDate(int year, int month, int day){
		
		month --;
		Calendar cal = Calendar.getInstance();
		
		cal.setLenient(false);
		
		try{
			cal.set(year, month, day);
			Date date = cal.getTime();
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}
%>
<%!
	public String getTitle(Calendar cal){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 mm월");
		return  sdf.format(cal);
	}
%>  
	
<%
	int action = 0;
	int nowMonth = 0;
	int nowYear = 0;
	
	Calendar c = Calendar.getInstance();
	Calendar cal = Calendar.getInstance();
	
	String actionParam = request.getParameter("action");
	
	if(actionParam == null){
		
		nowMonth = c.get(Calendar.MONTH);
		nowYear = c.get(Calendar.YEAR);
		cal.set(nowYear, nowMonth, 1);
		
	}else if (actionParam != null && actionParam.equals("0")){ //이전달
		
		cal.set(nowMonth, nowYear, 1);
		cal.add(Calendar.MONTH, -1);
		nowMonth = cal.get(Calendar.MONTH);
		nowYear = cal.get(Calendar.YEAR);
		
	}else if(actionParam != null && actionParam.equals("1")){ //다음달
		
		cal.set(nowMonth, nowYear, 1);
		cal.add(Calendar.MONTH, 1);
		nowMonth = cal.get(Calendar.MONTH);
		nowYear = cal.get(Calendar.YEAR);
		
	}
%>
<!DOCTYPE html>
<html>
<style>
	table{
		border-collapse: collapse;
		width: 70%;
		height: 300px;
		margin-left: 15%;
	}
	tr{
		text-align: center;
	}
	td{
		width: 100px;
	}
	.weekName{
		font-size: 20px;
		font-weight: bold;
	}
	#title{
		text-align : center;
		font-size: 30px;
		font-weight: bold;
	}
	.sunweek{
		color: red;
	}
	.satweek{
		color: blue;
	}
	img{
		width: 50px;
		height: 50px;
	}
</style>
<head>
<meta charset="UTF-8">
<title>Calendar & JSP</title>
</head>
<body>
<h2>JSP를 이용한 달력 구현</h2>
<pre>
1. 선택 년도와 월이 없으면, 현재 달력을 렌더링
2. 이전달과 다음달의 달력 처리
3. 임의의 년도와 월의 달력 처리
4. 제동한 언어의 목록 중 선택한 Locale 이용한 언어별 달력 처리
5. 제공한 TimeZone 목록 중 선택한 TimeZone을 이용하여 오늘 날짜에 대한 식별 처리
</pre>
<hr>
<table>
	<tr>
		<td>
			<a href = "calendar.jsp?month=<%=nowMonth%>&year=<%=nowYear%>&action=0">
				<img src = "left.png" alt = "leftButton">
			</a>
		</td>
		<td>
			<b><%= getTitle(cal)%></b>
		</td>
		<td>
			<a href = "calendar.jsp?month=<%=nowMonth%>&year=<%=nowYear%>&action=1">
				<img src = "right.png" alt = "rightButton">
			</a>
		</td>
	</tr>
</table>
<table id = "calendarTable" border = "1px solid gray" >
	<tr>
		<td class = "weekName sunweek">Sun</td>
		<td class = "weekName monweek">Mon</td>
		<td class = "weekName tueweek">Tue</td>
		<td class = "weekName wedweek">Wed</td>
		<td class = "weekName thuweek">Thu</td>
		<td class = "weekName friweek">Fri</td>
		<td class = "weekName satweek">Sat</td>
	</tr>
<%
	int nowDay;
	int cnt = 1;
	int dispDay = 1;
	String toDayColor;
	
	for(int w = 1; w < 7; w++){
		out.println("<tr>");
		for(int d = 1; d <8; d++){
			if(!(cnt >= cal.get(Calendar.DAY_OF_WEEK))){
				out.println("<td class = 'empty'>&nbsp;</td>");
				cnt++;
			}else{
				if(isDate(nowYear, nowMonth, dispDay)){
					if(dispDay == c.get(Calendar.DAY_OF_MONTH) && c.get(Calendar.MONTH) == cal.get(Calendar.MONTH) && c.get(Calendar.YEAR) == cal.get(Calendar.YEAR)){ //오늘						
						toDayColor = "toDayColor";
					}else{
						toDayColor = "";
					}
					out.println("<td class = '" + toDayColor + "'>" + dispDay + "<td>");
					
					cnt++;
					dispDay++;
					
				}else{
					out.println("<td class = 'empty'>&nbsp;</td>");
				}
			}
		}
		out.println("</tr>");
	}
%>
</table>
</body>
</html>