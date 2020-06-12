<!--修改个人信息界面，王伟彬  -->
<%@page import="com.task4.Applicant"%>
<%@page import="com.task3.*"%>
<%@page import="com.task2.ApplicantDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<title>修改基本信息</title>


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
	
	<div class="col-md-5 col-md-offset-3">
	
		<div class="panel panel-default">
			<!-- 标题 -->
			<div class="panel-heading">
				<h3 class="panel-title">修改基本信息</h3>
			</div>

			<div class="panel-body">

				<div class="panel-body">
					<!-- 表单 -->
					<form action="ResumeBasicinfoServlet?type=update" method="post" class="form-horizontal">
					
					<%
						int applicantID = Integer.valueOf(request.getParameter("id2"));
						ResumeDAO dao = new ResumeDAO();
						ResumeBasicinfo re = dao.select(applicantID);
					
					
					%>
					
					
					
					
					
						<div class="form-group">
							<div class="col-sm-8">
								<label>姓名:</label> <input type="text" class="form-control"
									name="name" id="name" placeholder="请输入姓名" value=<%=re.getName() %>>
							</div>
						</div>



						<div class="form-group">
							<div class="col-sm-8">
								<label>电话:</label> <input type="text" class="form-control"
									name="phone" id="phone" placeholder="请输入电话号码" value=<%=re.getPhone() %>>
							</div>


						</div>


						<div class="form-group">
							<div class="col-sm-8">
								<label>邮箱:</label> <input type="text" class="form-control"
									name="email" id="email" placeholder="请输入邮箱" value=<%=re.getEmail() %>>
							</div>
						</div>
							
							<div class="form-group">
							<div class="col-sm-8">
								<label>身份证：</label> <input type="text" class="form-control"
									name="idcard" id="idcard" placeholder="请输入邮箱" value=<%=re.getIdcard()%>>
							</div>
							</div>
							
								<div class="form-group">
							<div class="col-sm-8">
								<label>地址：</label> <input type="text" class="form-control"
									name="address" id="address" placeholder="请输入邮箱" value=<%=re.getAddress()%>>
							</div>
							</div>
						
							
								<div class="form-group">
							<div class="col-sm-8">
								<label>部门：</label> <input type="text" class="form-control"
									name="bumen" id="bumen" placeholder="请输入邮箱" value=<%=re.getBumen()%>>
							</div>
								</div>
						
						
								<div class="form-group">
							<div class="col-sm-8">
								<label>职称:</label> <input type="text" class="form-control"
									name="zhicheng" id="zhicheng" placeholder="请输入邮箱" value=<%=re.getZhicheng()%>>
							</div>


						</div>

						<div class="form-group">
						<div class="col-sm-8"><label>性别:</label><br> 
						<%
							String sex = re.getSex();
							if("male".equals(sex)){
								%>
								<label class="radio-inline"><input type="radio" name="sex" value="male" checked="checked">男
								</label> <label class="radio-inline"> <input type="radio"
									name="sex" value="female">女
								</label>
								<%
							}else{
								%>
								<label class="radio-inline"><input type="radio" name="sex" value="male">男
								</label> <label class="radio-inline"> <input type="radio"
									name="sex" value="female"  checked="checked">女
								</label>
								
								<%
								
							}
						%>
							
								
								
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-10">
								<button type="submit" class="btn btn-primary">修改</button>
								<button type="reset" class="btn btn-warning">重置</button>
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