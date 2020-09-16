<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	h2{
		text-align: center;
		padding-top: 50px;
	}
	#container{
		padding: 50px 40%;
	}
	#signup, #cancle{
		margin-top: 20px;
		margin-left : 20%;
	}
</style>
<title>회원 관리(Client Side)</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<style>
</style>
</head>
<body>
	<h2>회원 가입</h2>
	<div id = "container">
		<!-- 아이디 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
   				 <span class="input-group-text">아이디 : </span>
  			</div>
	  		<input type="text" class="form-control" placeholder="ID :D" aria-describedby="button-addon2" name = "mem_id">
	  		<div class="input-group-append">
	    		<button class="btn btn-outline-secondary" type="button" id="button-addon2">중복 검사</button>
	  		</div>
		</div>
		
		<!-- 비밀번호 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
   				 <span class="input-group-text">비밀번호 : </span>
  			</div>
	  		<input type="text" class="form-control" placeholder="pass :D"  name = "mem_pass">
		</div>
		
		<!-- 이름 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
   				 <span class="input-group-text">이름 : </span>
  			</div>
	  		<input type="text" class="form-control" placeholder="name :D" name = "mem_name">
		</div>
		
		<!-- 주민번호 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
   				 <span class="input-group-text">주민번호 : </span>
  			</div>
	  		<input type="text" class="form-control" placeholder="front regno :D" name = "mem_regno1" >
	  		<div class="input-group-prepend">
   				 <span class="input-group-text">― </span>
  			</div>
	  		<input type="text" class="form-control" placeholder="back regno :D" name = "mem_regno2" >
		</div>
		
		<!-- 우편번호 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
   				 <span class="input-group-text">우편번호 : </span>
  			</div>
	  		<input type="text" class="form-control" placeholder="zip :D" name = "mem_zip">
		</div>
		
		<!-- 주소 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
   				 <span class="input-group-text">주소 : </span>
  			</div>
	  		<input type="text" class="form-control" placeholder="addr :D" name = "mem_add1">
	  		<div class="input-group-append">
	    		<button class="btn btn-outline-secondary" type="button" id="button-addon2">주소 찾기</button>
	  		</div>
		</div>
		
		<!-- 상세주소 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
   				 <span class="input-group-text">상세주소 : </span>
  			</div>
	  		<input type="text" class="form-control" placeholder="detailed addr :D" name = "mem_add2">
		</div>
		
		<!-- 집 전화번호 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
   				 <span class="input-group-text">집 전화번호 : </span>
  			</div>
	  		<input type="text" class="form-control" placeholder="homeTel :D" name = "mem_hometel">
		</div>
		
		<!-- 회사 전화번호 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
   				 <span class="input-group-text">회사 전화번호 : </span>
  			</div>
	  		<input type="text" class="form-control" placeholder="comTel :D" name = "mem_comtel">
		</div>
		
		<!-- 휴대폰 번호 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
   				 <span class="input-group-text">휴대전화 번호 : </span>
  			</div>
	  		<input type="text" class="form-control" placeholder="hp :D" name = "mem_hp">
		</div>
		
		<!-- 이메일 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
   				 <span class="input-group-text">이메일 : </span>
  			</div>
	  		<input type="text" class="form-control" placeholder="e-mail :D" name = "mem_mail">
		</div>
		
		<!-- 직업 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
   				 <span class="input-group-text">직업 : </span>
  			</div>
	  		<input type="text" class="form-control" placeholder="e-mail :D" name = "mem_mail">
		</div>
		
		<!-- 취미 -->
		<div class="input-group mb-3">
		  <div class="input-group-append">
		  		<label class="input-group-text" for="inputGroupSelect02">취미 : </label>
		  </div>
		 	<select class="custom-select" id="inputGroupSelect02" name = "mem_like">
			    <option selected>hobby :D</option>
			    <option value="movie">영화</option>
			    <option value="camping">캠핑</option>
			    <option value="read">독서</option>
			    <option value="sports">운동</option>
			    <option value="cook">요리</option>
			    <option value="trip">여행</option>
		  </select>
		</div>
		
		<!-- 기념일이름 -->
		<div class="input-group mb-3">
		  <div class="input-group-append">
		  		<label class="input-group-text" for="inputGroupSelect02">기념일 이름 : </label>
		  </div>
		 	<select class="custom-select" id="inputGroupSelect02" name = "mem_memorial">
			    <option selected>memorial :D</option>
			    <option value="1">결혼기념일</option>
			    <option value="2">아버지생신</option>
			    <option value="3">어머니생신</option>
			    <option value="4">내생일</option>
		  </select>
		</div>
		
		<!-- 기념일 -->
		<div class="input-group mb-3">
			<div class="input-group-append">
		  		<label class="input-group-text" for="inputGroupSelect02">기념일 : </label>
		  	</div>
        	<input type="date" class="form-control" name = "mem_memorialday">
        </div>
        
        <div class="input-group mb-3">
        	<button id = "cancle" type="button" class="btn btn-secondary">취소하기</button>
        	<button id ="signup" type="button" class="btn btn-success">가입하기</button>
        </div>
	</div><!-- container 태그 종료 -->
</body>
</html>