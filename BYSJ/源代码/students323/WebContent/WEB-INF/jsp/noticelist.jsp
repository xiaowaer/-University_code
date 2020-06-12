 <%@ page contentType="text/html;charset=UTF-8" language="java"  import="ljj.pojo.Notice" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>后台登录</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <%--<meta http-equiv="Cache-Control" content="no-siteapp" />--%>

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
        <a href="findNotice">通知信息</a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="findNotice" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>    
</div>

<div class="x-body">
    <div class="layui-row searchform" >
        <form class="layui-form layui-col-md12 x-so" action="findNotice" id="searchform1" >
            <input class="layui-input" placeholder="请输入发布者" name="publicsher" id="publicsher">
            <input class="layui-input" placeholder="请输入发布标题" name="noticetitle" id="noticetitle">
            <input class="layui-input" type="hidden" name="pageIndex" value="1">
            <input class="layui-input" type="hidden" name="pageSize" value="5">
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
    
     <xblock>
        <button id="addNoticeBtn" class="layui-btn layui-btn-normal"> <i class="layui-icon">&#xe654;</i>添加 </button>
        
        <span class="x-right" style="line-height:40px">共有数据：${pi.totalCount} 条</span>
    </xblock>
    
    
    <%--添加模态框--%>
    <div class="layui-row" id="test" style="display: none;">
        <div class="layui-col-md10">
            <form class="layui-form" id="addNoticeForm">
            
               <div class="layui-form-item" style="margin-top:20px;">
                    <label  class="layui-form-label">发布者</label>
                        <div class="layui-input-block lafite_width_60">
                            <select name="publisher" lay-verify="required">
                                <option value=""></option>
                                <option value="0" selected>ljj</option>
                            </select>    
                        </div>
                    </div>
                    
                     <div class="layui-form-item" style="margin-top:80px;">
                    <label class="layui-form-label">发布类型</label>
                    <div class="layui-input-block">
                        <input type="text" name="publishtype" class="layui-input" placeholder="发布类型"> 
                    </div>
                </div>
                    
                          <div class="layui-form-item" style="margin-top:80px;">
                    <label class="layui-form-label">通知标题</label>
                    <div class="layui-input-block">
                        <input type="text" name="publishtitle" class="layui-input" placeholder="发布类型"> 
                    </div>
                </div>

                    <div class="layui-form-item">
                        <label  class="layui-form-label">通知内容</label>
                        <div class="layui-input-block">
                            <textarea id="uditcontent" name="desc" placeholder="请输入内容"  style ="height:300px;width:700px"></textarea>    
                        </div>
                    </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo"style ="width:180px;" >提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary"style ="width:180px;">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    
    
    
    <%--表格数据--%>
    <table class="layui-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>内容</th>
            <th>标题</th>
            <th>发布者</th>
            <th>发布时间</th>
            <th>发布类型</th>
             <th>操作</th>
        </thead>
        <tbody>
<c:forEach items="${pi.list}" var="notice">
        <tr>
            <td>${notice.noticeid}</td>
            <td>${notice.noticecontent}</td>
            <td>${notice.noticetitle}</td>
            <td>${notice.publisher}</td>
            <td>${notice.publishtime}</td>
            <td>${notice.noticetype}</td>   
            <td>
                <a title="编辑"    id= "updateEdit"    href="/findStudentById?s_id=${reader.readerid}">
                    <i class="layui-icon">&#xe642;</i>
                </a>
                <a title="删除" onclick="member_del(this,'${reader.readerid}')" href="javascript:;">
                    <i class="layui-icon">&#xe640;</i>
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
 	$(function(){
 		  var ue = UE.getEditor('uditcontent');	
 	});
      
    </script>
    
<script>

    layui.use(['jquery','form','layer','laydate'], function(){
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate;

        /*添加弹出框*/
        $("#addNoticeBtn").click(function () {
            layer.open({
                type:1,
                title:"添加通知",
                skin:"myclass",
                area:['900px', '860px'],
                anim:2,
                content:$("#test").html()
            });

            form.on('submit(formDemo)', function(data) {									
                // layer.msg('aaa',{icon:1,time:3000});
                var param=data.field;
                if(param.publisher=="0"){
                	var publisher="ljj";
                }
                var noticetype=param.publishtype;
                var noticetitle=param.publishtitle;
                var noticecontent=UE.getEditor('uditcontent').getContent();
                console.log(JSON.stringify(param));
                console.log(publisher);
                console.log(noticetype);
                console.log(noticecontent);
                console.log(noticetitle);
            	
                
                
                $.ajax({
                    url: '/students323/addNotice',
                    data:"{\"noticetype\":\""+noticetype+"\",\"noticecontent\":\""+noticecontent+"\",\"noticetitle\":\""+noticetitle+"\",\"publisher\":\""+publisher+"\"}",
    				dataType:'json',//服务器返回json格式数据
    				type:'post',//HTTP请求类型
    				timeout:10000,//超时时间设置为10秒；
    				headers:{'Content-Type':'application/json'},
                    success:function(data){
                    	if(data.result=="ok"){ layer.msg('添加成功', {icon: 1, time: 3000});
                        setTimeout(function () {window.location.href='findNotice';},2000);
                        }
                    },
                    error:function(){
                        layer.msg('添加失败',{icon:0,time:3000});
                        setTimeout(function () {window.location.href='findNotice';},2000);
                    }
                });
                // return false;
            });
        }); 

    });


    /*删除*/
    function member_del(obj,c_id){
        layer.confirm('确认要删除吗？',function(index){
            //发异步删除数据
           $.get("/deleteClass",{"c_id":c_id},function (data) {
                if(data =true){
                    layer.msg('删除成功!',{icon:1,time:2000});
                  setTimeout(function () {window.location.href='/findClass';},2000);

                }else {
                    layer.msg('删除失败!',{icon:1,time:2000});
                    setTimeout(function () {window.location.href='/findClass';},2000);
                }
            });
        });
    }

</script>


</body>


</html>
