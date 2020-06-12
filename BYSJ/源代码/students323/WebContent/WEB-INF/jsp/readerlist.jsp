
<%@ page contentType="text/html;charset=UTF-8" language="java"  import="ljj.pojo.Reader" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
  <title>读者列表</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />

    <link rel="icon" href="/images/favicon.ico" sizes="32x32" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
    <script src="lib/layui/layui.js"></script>
    <script type="text/javascript" src="js/xadmin.js"></script>
    
        <style type="text/css">
        .layui-table{
                text-align: center;
            }
        .layui-table th{
            text-align: center;
        }
        .layui-table td, .layui-table th {
    position: relative;
    min-height: 20px;
    line-height: 20px;
    font-size: 20px;
    padding: 9px 15px;
    padding-top: 9px;
    padding-right: 15px;
    padding-bottom: 9px;
    padding-left: 15px;
}
   .searchform>form>input:last-child:hover{
    background-color: #f57421;
    opacity: 0.7;
    cursor: pointer;
    font-size: 18px;
}
        
    </style>
    
</head>
<body>

<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="findReader">读者信息</a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="findReader" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>    
</div>

<div class="x-body">
    <div class="layui-row searchform" >
        <form class="layui-form layui-col-md12 x-so" action="findReader" id="searchform1" >
            <input class="layui-input" placeholder="请输入昵称" name="nick" id="r_nick">
            <input class="layui-input" placeholder="请输入性别" name="sex" id="r_sex">
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
            <%-- <%-- <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th> --%>
            <th>ID</th>
            <th>昵称</th>
            <th>电话</th>
            <th>阅读倾向</th>
            <th>城市</th>
             <th>操作</th>
        </thead>
        <tbody>
<c:forEach items="${pi.list}" var="reader">
        <tr>
            <td>${reader.readerid}</td>
            <td>${reader.nick}</td>
            <td>${reader.telephone}</td>
            <td>${reader.likeread}</td>
            <td>${reader.city}</td>
            
            <td>
                  <a title="编辑"   onclick="editreader(this,'${reader.readerid}')" href="javascript:(0);" >  
                 <i class="layui-icon">&#xe60a;</i>
                   <span>查看<span/> 
                </a>
                 
              	<a title="删除" onclick="deletereader(this,'${reader.readerid}')" href="javascript:(0);">
                    <i class="layui-icon">&#xe640;</i>
                    <span>删除<span/> 
                </a>
                  	<a title="设置读者类型" onclick="settingreadertype(this,'${reader.readerid}')" href="javascript:(0);">
                    <i class="layui-icon">&#xe640;</i>
                    <span>编辑读者类型<span/> 
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
    
	<script type="text/javascript">
	
	function settingreadertype(obj,readerid){
		layer.open({
		       type:2,
		       title:"设置读者类型",
		       skin:"",
		       area:['900px', '700px'],
		       anim:2,
		      content:"settingReaderType?readerid="+readerid,
		   });    
		}
	
	function editreader(obj,readerid){
		layer.open({
		       type:2,
		       title:"查看读者信息",
		       skin:"",
		       area:['900px', '700px'],
		       anim:2,
		      content:"findReaderById?readerid="+readerid,
		   });    
		}
	
	function deletereader(obj,readerid){
		   var readerid=readerid;
		   console.log(readerid);
		   $.ajax({
		        url: '/students323/deleteReader',
		        data:"{\"readerid\":\""+readerid+"\"}",
				dataType:'json',//服务器返回json格式数据
				type:'post',//HTTP请求类型
				timeout:10000,//超时时间设置为10秒；
				contentType:'application/json;charset=UTF-8',
		        success:function(data){
		        	if(data.status=="ok"){
					console.log(JSON.stringify(data));
		        	layer.msg('删除成功!',{icon:1,time:2000});
	                setTimeout(function () {window.location.href='findReader';},2000);}       
		        	},
		        error:function(){
		        	 layer.msg('删除失败',{icon:0,time:3000});
		        }
		    }); 

			}

	
	
	$('#searchform1').submit(function() {
		var sex=$("#r_sex").val();
		console.log(sex);
		 if(sex!==""&&sex!=="男"&&sex!=="女")
		 {
		  alert("请输入在性别框中输入男或者女！");
		  return false;
		 }
		});

	
		 

	</script>
</body>
</html>