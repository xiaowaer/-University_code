<!-- 登陆界面，黄宇航 -->
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册页面</title>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">

<script type="text/javascript">
	function validate(){
		var email = document.getElementById("applicantname");
		var password = document.getElementById("password");
		var agree = document.getElementById("agree");
	
		alert("账号不能为空！");
		if(applicantname.value.equals(" ")){
			alert("账号不能为空！");
			email.focus();
			return false;
		}
		if(password.value == ""){
			alert("密码不能为空！");
			password.focus();
			return false;			
		}else if(password.value.length < 6 || password.value.length > 12){
			alert("密码长度不符合要求，请输入6~12位密码！");
			password.focus();
			return false;
		}
		
		return true;
	}
	
	
	function CheckApplicantname(){
		importPackage(com.task2);
		var email = document.getElementById("applicantname");
		
		ApplicantDAO dao = new ApplicantDAO();
		System.out.println("开始链接sql");
		//判断邮箱是否已经注册
		 flag = dao.isExistApplicantname(applicantname);
		 ystem.out.println("flag");
		if(flag){
			//邮箱被注册
			System.out.println("注册失败");
			alert("账号已经被注册，请重新输入！");
			return false;
	}else{
		alert("恭喜您，账号可用！")
	}
	}
	
	
	function changeValidateCode() {
		document.getElementById("validateCode").src = "ValidateCodeServlet?rand=" + Math.random();
		
	}

</script>
</head>
<body>

	<div class="row login-box">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-success">

				<div class="panel-heading" align="center">注册</div>

				<div class="panel-body">
				<!--表单  -->	<!--表单  -->	<!--表单  -->
					<form action="ApplicantRegisterServlet" method="post" >
				<!--表单  -->	<!--表单  -->	<!--表单  -->
						

						<div class="form-group">
						<label>账&nbsp;号:</label> 
							<div class="form-group">
								<input type="text" class="form-control"
									name="applicantname" id="applicantname">
							</div>
						</div>

						<div class="form-group">
							<label>密&nbsp;码:</label> <input type="password" class="form-control"
								name="password" id="password">
						</div>


						<div class="form-group">


							<label>请输入下面的验证码:</label> <input type="text" class="form-control"
								name="verifyCode"><span><img src="ValidateCodeServlet"
								id="validateCode" title="单击换一换" onclick="changeValidateCode()">
								<a href="javascript:window.location.reload();">看不清？</a></span></div>


						<br>

						<div class="form-group">
							<div class="col-md-4 col-md-offset-3">
								<input type="submit" class="btn btn-success" name=""
									value="立即注册">
							</div>
							<div>
								<input type="reset" class="btn btn-warning" name="" value="重置">
							</div>
						</div>
					</form>
					<div class="form-group">
						<p align="center">
							<br> <b>已有账号？</b><a href="login.jsp">登录</a>
						</p>
					</div>

				</div>
			</div>
		</div>

	</div>

</body>

</html>