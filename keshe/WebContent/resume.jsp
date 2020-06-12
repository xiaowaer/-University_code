<!--添加基本信息 ，王伟彬-->
<%@page import="com.task4.Applicant"%>
<%@page import="com.task2.ApplicantDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<title>添加我的基本信息</title>
</head>
<body>
	
	<%
		
		String applicantname = (String)request.getSession().getAttribute("applicantname");
		if("000".equals(applicantname) ){
			%>
			<jsp:include page="AdminTop.jsp"></jsp:include>
			<%
		}else{
			%><jsp:include page="top.jsp"></jsp:include>
			<% 
		}
	%>
	<div>
	
	<div class="col-md-8 col-md-offset-2">
	
		<div class="panel panel-default">
			<!-- 标题 -->
			<div class="panel-heading">
				<h3 class="panel-title">添加我的基本信息</h3>
			</div>

			<div class="panel-body">

				<div class="panel-body">
				
									<!-- 表单 --><!-- 表单 --><!-- 表单 -->
					<form action="ResumeBasicinfoServlet?type=add" method="post" class="form-horizontal">
									<!-- 表单 --><!-- 表单 --><!-- 表单 -->
					
						<div class="form-group">
							<div class="col-sm-8">
								<label>姓名:</label> <input type="text" class="form-control"
									name="name" id="name" placeholder="请输入姓名">
							</div>
						</div>



						<div class="form-group">
							<div class="col-sm-8">
								<label>电话:</label> <input type="text" class="form-control"
									name="phone" id="phone" placeholder="请输入您的电话号码">
							</div>


						</div>


						<div class="form-group">
							<div class="col-sm-8">
								<label>邮箱:</label> <input type="text" class="form-control"
									name="email" id="email" placeholder="请输入您的邮箱">
							</div>

						</div>
						
								<div class="form-group">
							<div class="col-sm-8">
								<label>地址:</label> <input type="text" class="form-control"
									name="address" id="address" placeholder="请输入您的地址">
							</div>

						</div>
						
								<div class="form-group">
							<div class="col-sm-8">
								<label>身份证:</label> <input type="text" class="form-control"
									name="idcard" id="idcard" placeholder="请输入您的身份证">
							</div>

						</div>
						
								<div class="form-group">
							<div class="col-sm-8">
								<label>部门:</label> <input type="text" class="form-control"
									name="bumen" id="bumen" placeholder="请输入您的部门">
							</div>

						</div>
						
								<div class="form-group">
							<div class="col-sm-8">
								<label>职称:</label> <input type="text" class="form-control"
									name="zhicheng" id="zhicheng" placeholder="请输入您的职称">
							</div>

						</div>
						
						
						

						<div class="form-group">
							<div class="col-sm-8">
								<label>性别:</label><br> <label class="radio-inline">
									<input type="radio" name="sex" value="male">男
								</label> <label class="radio-inline"> <input type="radio"
									name="sex" value="female">女
								</label>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-10">
								<button type="submit" class="btn btn-primary">添加</button>

								<button type="reset" class="btn btn-warning">清空</button>
							</div>
						</div>

					</form>


				</div>
			</div>
		</div>
		
		</div>
		
	</div>


</body>
</html>