
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录界面</title>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">


</head>
<body>

	<%
		String applicantEmail = "";
		String applicantPwd = "";

		//获取cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("COOKIE_APPLICANTEMAIL")) {
					applicantEmail = cookie.getValue();
				}
				if (cookie.getName().equals("COOKIE_APPLICANTPWD")) {
					applicantPwd = cookie.getValue();
				}
			} //for
		} //if
	%>
	
	<!-- 登录 -->
	<div class="row login-box">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-primary">

				<div class="panel-heading" align="center">登录</div>

				<div class="panel-body">

					<form action="ApplicantLoginServlet" method="post"
						onsubmit="return validate();">

						<div class="form-group">
							<label>工&nbsp;号:</label> <input type="text" class="form-control"
								name="applicantname" id="applicantname" value="<%=applicantEmail%>">
						</div>
						<div class="form-group">
							<label>密&nbsp;码:</label> <input type="password" class="form-control"
								name="password" id="password" value="<%=applicantPwd%>">
						</div>
						<div class="form-group">
							<span> <label class="checkbox-inline"> <input checked="checked"
									type="checkbox" name="rememberMe" value="true">记住密码？
							</label>
							</span>
						</div>

						<div class="form-group">
							<div class="col-md-4 col-md-offset-3">
								<input type="submit" class="btn btn-primary" name="" value="登录">
							</div>
							<div>
								<input type="reset" class="btn btn-warning" name="" value="重置">
							</div>
						</div>
					</form>
					<div class="form-group">
						<p align="center">
							 <a href="adminlogin.jsp">管理员登录</a><br><b>还没有账号？</b><a href="register.jsp">立即注册</a>
						</p>
					</div>
			</div>
		</div>
		</div>
	</div>
</body>
</html>