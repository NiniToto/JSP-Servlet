<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<script type="text/javascript" src = "<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script type="text/javascript" src = "<%=request.getContextPath()%>/js/sessionTimer.js"></script>
<title>회원 관리</title>
</head>
<style>
	.container{
		margin-left: 40%;
	}
</style>
<body>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post" action="">
					<h3 style="text-align: center;">회원 관리 양식</h3>
					<div class="input-group mb-3">
					  <input type="text" class="form-control" placeholder="아이디 입력" name = "mem_id" aria-label="Recipient's username" aria-describedby="button-addon2" maxlength = "50">
					  <div class="input-group-append">
					    <button class="btn btn-outline-secondary" type="button" id="button-addon2">중복검사</button>
					  </div>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호 입력" name="mem_pass" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름 입력" name="mem_name" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="주민번호 앞자리 입력" name="mem_regno1" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="주민번호 뒷자리 입력" name="mem_regno2" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="생일 입력" name="mem_bir" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="우편번호 입력" name="mem_zip" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="주소1 입력" name="mem_add1" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="주소2 입력" name="mem_add2" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="집 전화번호 입력" name="mem_hometel" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="공용번호 입력" name="mem_comtel" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="휴대번호 입력" name="mem_hp" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이메일 입력" name="mem_mail" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="직업 입력" name="mem_job" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="취미 입력" name="mem_like" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="기념일 이름 입력" name="mem_memorial" maxlength="50">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="기념일 입력" name="mem_memorialday" maxlength="50">
					</div>
					<input type="submit" class="btn btn-primary form-control" value="회원가입">
				</form>
			</div>
		</div>
	</div>
</body>
</html>



