<%@ page contentType="text/html;charset=UTF-8" language="java"  import="ljj.pojo.BysjComment" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>后台登录</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="icon" href="/images/favicon.ico" sizes="32x32" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="./js/jquery-1.3.2.min.js"></script>
      <!-- 配置文件 -->
    <script type="text/javascript" src="lib/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="lib/ueditor/ueditor.all.js"></script>
    <script src="lib/layui/layui.js"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>

    <style type="text/css">
        .layui-table{
                text-align: center;
            }
        .layui-table th{
            text-align: center;
        }
    </style>


    
</head>

<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="findComment">新闻信息</a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="findComment" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>    
</div>
     <xblock>
        <span class="x-right" style="line-height:40px">共有数据：${pi.totalCount} 条</span>
    </xblock>
    
<div class="x-body">
    <div class="layui-row searchform" >
        <form class="layui-form layui-col-md12 x-so" action="findComment" id="searchform1" >
        	<input class="layui-input" placeholder="请输入target" name="target" id="target">
            <input class="layui-input" placeholder="请输入评论人" name="commenter" id="commenter">
            <input class="layui-input" placeholder="请输入内容" name="cmcontent" id="cmcontent">   
            <input class="layui-input" type="hidden" name="pageIndex" value="1">
            <input class="layui-input" type="hidden" name="pageSize" value="10">
            <input id="login" class="tijiao" type="submit" value="提交" style="
	outline: 0;
	background: #0dc316;
	width: 6%;
	border: 0;
	padding: 9px;
	color: #FFFFFF;
	font-size: 15px;
	">
        </form>   
    </div>
    
    <%--表格数据--%>
    <table class="layui-table">
        <thead>
        <tr>
            <th>评论号</th>
            <th>头像</th>
            <th>评论者</th>
            <th>父评论号</th>
            <th>评论内容</th>
            <th>评论页面</th>
             <th>操作</th>
        </thead>
        <tbody>
<c:forEach items="${pi.list}" var="comment">
        <tr>
            <td>${comment.commentid}</td>
             <td><img src="${comment.cmhead}" /></td>
            <td>${comment.commenter}</td>     
            <td>${comment.parent}</td>
            <td>${comment.cmcontent}</td>
            <td>${comment.target}</td>
         
            <td>
              	<a title="删除" onclick="deletecomment(this,'${comment.commentid}')" href="javascript:(0);">
                    <i class="layui-icon">&#xe640;</i>
                    <span>删除<span/> 
                </a>
           
            </td>
        </tr>
</c:forEach>
        </tbody>
    </table>
    
<div class="" >
    <input type="hidden" id="totalPageCount" value="${pi.pageTotalCount}"/>
    <c:import url="pageBtn.jsp">
        <c:param name="totalCount" value="${pi.totalCount}"/>
        <c:param name="currentPageNo" value="${pi.pageIndex}"/>
        <c:param name="totalPageCount" value="${pi.pageTotalCount}"/>
    </c:import>
</div>
</div>

   <script>
   function deletecomment(obj,commentid){
	   var commentid=commentid;
	   console.log(commentid);
	   $.ajax({
	        url: '/students323/deleteMyComment?commentid='+commentid,
	        data:{},
			dataType:'json',//服务器返回json格式数据
			type:'get',//HTTP请求类型
			timeout:10000,//超时时间设置为10秒；
			contentType:'application/json;charset=UTF-8',
	        success:function(data){
	        	if(data.status=="ok"){
				console.log(JSON.stringify(data));
	        	layer.msg('删除成功!',{icon:1,time:2000});
                setTimeout(function () {window.location.href='findComment';},2000);}       
	        	},
	        error:function(){
	        	 layer.msg('删除失败',{icon:0,time:3000});
	        }
	    }); 

		}

   </script>

    


</body>


</html>