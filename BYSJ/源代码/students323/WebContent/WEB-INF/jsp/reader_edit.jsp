<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>修改信息</title>
    <link rel="icon" href="/images/favicon.ico" sizes="32x32" />

 <!--    <link rel="stylesheet" href="/css/xadmin.css"> -->
       <link rel="stylesheet" href="lib/layui/css/layui.css">
    <script type="text/javascript" src="./js/jquery-1.3.2.min.js"></script>
    <script src="lib/layui/layui.js"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
      <style type="text/css">

        p{  
               font-size: 30px;
    				padding: 9px 15px;
    				width: 100%;
				    line-height: 20px;
                    text-align: center;
            }
            
  body{
   background:#fff url(https://xiaoliwaer.top:525/readerinfobg.gif) no-repeat left top;
  background-size:100%;
  }
            
 .round_icon{
 margin: 0 auto;
  width: 180px;
  height: 180px;
  display: flex;
  border-radius: 50%;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}
.content{
    text-align: center;
}


    </style>
        <script type="text/javascript" >
 
        </script>
    

</head>

<body>

<div class="x-body">
   
        <div style="margin-top:20px;">
          		<div class="head" >
          		<img src="https://xiaoliwaer.top:525/headerimg/${user.head}" class="round_icon"  alt="">
          		</div>
          		
          		<div class="content" >
                <p  style="margin-top:30px;">ID:${reader.readerid} </p>
             	<p >昵称:${reader.nick} </p>
             	<p >生日:${reader.birthday} </p>
             	<p >城市:${reader.city} </p>
             	<p >性别:${reader.sex} </p>
             	<p >工作:${reader.work} </p>
             	<p >阅读喜好:${reader.likeread} </p>
             	<p >简介:${reader.intro} </p>
             	<p >手机号:${reader.telephone} </p>
          		</div>
         
        </div>
        

    
   
</div>

<script>
</script>
</body>
</html>