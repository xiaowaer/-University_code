ResumeBasicinfo<%@page import="com.task3.ResumeDAO"%>
<%@page import="com.info.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>网站头部文件</title>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/resume.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">人事信息管理系统<c:out value="${ sessionScope.applicantname}" default="游客"></c:out></a>
	</div>
	<div class="collapse navbar-collapse">
		<%
			if (session.getAttribute("applicantname") == null) {
				/*
				 *	还未登录
				*/
		%>
		<ul class="nav navbar-nav"><li><a href="./allIndex.jsp">首页</a></li></ul>

		<ul class="nav navbar-nav  navbar-right">
			<li><a href="./login.jsp"><span class="glyphicon glyphicon-user" style="font-size: 18px;"> 登录</span></a></li>
			<li><a href="./register.jsp"><span class="glyphicon glyphicon-send" style="font-size: 18px;"> 注册</span></a></li>
		</ul>
		<%
			} else {
				/*
				 *	已经登录
				*/
		%>
		
		<ul class="nav navbar-nav"><li><a href="allIndex.jsp">首页</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">我的信息 <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<%		ResumeDAO re_dao = new ResumeDAO();
							
							int applicantID = (Integer) request.getSession().getAttribute("applicantID");
							System.out.println("applicantID是：" + applicantID);
							/*查询是否添加个人信息*/
							if (re_dao.select(applicantID) == null) {
							
					%>
					<li><a href="resume.jsp">添加我的个人信息</a></li>
					<%
						} else {
					%>
					<li><a href="index.jsp">我的个人信息</a></li>
					<li class="divider"></li>
				
					
				 <%	
 	}
 		%></ul></li>
 		

		<li><a href="./ContactServlet">通讯录</a></li>
 		
	</ul>


		<ul class="nav navbar-nav  navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-user"
					style="font-size: 18px;"> <%=session.getAttribute("applicantname") %></span></a>
			</li>
			<li><a href="out.jsp"><span class="glyphicon glyphicon-send"
					style="font-size: 18px;"> 安全退出</span></a></li>
		</ul>
		<%
			}
		%>
	</div>
	</nav>

</body>
</html>