<%@page import="com.admin.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工管理</title>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/resume.css" type="text/css" rel="stylesheet" />
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="/scripts/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
		int num = 0;
	%>
	
	
	
	
	
	

	<div  class="row login-box">
       <div class="col-md-8 col-md-offset-2">
            <div class='panel panel-primary'>
                 <div class="panel-heading">员工管理 </div>
                 <table class="table table-hover table-bordered" contenteditable="true">
                   <thead>
                     <tr>
                     	<th>序号</th>
                     	<th>姓名</th>
                       <th>用户名</th>
                      <!--   <th>员工号</th> -->
                       <th>操作</th>
                     </tr>
                   </thead>
                   <tbody>
                   <%	List<User> list = (List<User>)request.getAttribute("userList");
                   		for(User user : list){		
                   %> <tr>
                     <td><%=++num	%></td>
                     <td><%=user.getName() %></td>
                       
                       <td><%=user.getApplicantname()	%></td>

                      
                    
                       <td>
                       		
                       		<a href="ResumeBasicinfoServlet?type=select&&id=<%=user.getApplicantname()%>" class="btn btn-info btn-xs" type="button">查看信息</a>
                       		<a href="ApplicantServlet?type=delete&&id6=<%=user.getApplicantname()%>" class="btn btn-danger btn-xs" type="button">删除员工</a>
                       
             
                       </td>
                     </tr>
                    <%	} %>
                   </tbody>
                 </table>
				
            </div>
            <br>
       </div>
   </div>

</body>
</html>