<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登陆界面</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>

<div id="wrapper" class="login-page">
<div id="login_form" class="form">
<form id="loginform" class="login-form"  action="adlogin" method="post">
 <h2>资讯新闻阅读APP后台管理员登录</h2>
 
<span class="erro">${msg}</span>
<input type="text" placeholder="用户名"  name="nick" id="nick" />
<input type="password" placeholder="密码"  name="password" id="mima" />
<input id="login" class="denglu" type="submit" value="登陆" style="
	text-transform: uppercase;
	outline: 0;
	background: #0dc316;
	width: 100%;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 15px;
	
	">
</form>
</div>
</div>

<script src="js/jquery.min.js"></script>
<script type="text/javascript">

$('#loginform').submit(function() {
	var nick=$("#nick").val();
	 var mima=$("#mima").val(); 
	 if(nick==""||mima=="")
	 {
	  alert("请填写好账号密码！");
	  return false;
	 }	
	});






	</script>
</body>
</html>