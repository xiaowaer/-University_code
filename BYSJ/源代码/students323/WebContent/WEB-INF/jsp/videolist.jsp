<%@ page contentType="text/html;charset=UTF-8" language="java"  import="ljj.pojo.Video" %> 
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
        <a href="findVideo">视频信息</a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="findVideo" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>    
</div>

<div class="x-body">
    <div class="layui-row searchform" >
        <form class="layui-form layui-col-md12 x-so" action="findVideo" id="searchform1" >
            <input class="layui-input" placeholder="请输入标题" name="videoname" id="videoname">
            <input class="layui-input" placeholder="请输入分类" name="cid" id="cid">
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
            <th>ID</th>
            <th>标题</th>
            <th>来源</th>
            <th>分类</th>
            <th>首图</th>
            <th>抓取时间</th>
             <th>操作</th>
        </thead>
        <tbody>
<c:forEach items="${pi.list}" var="video">
        <tr>
            <td>${video.videoid}</td>
            <td>${video.videoname}</td>
            <td>${video.videoorigin}</td>
            <td>${video.cid}</td>
            <td><img src="${video.videoheadurl}" /></td>
            <td>${video.videoctime}</td>     
            <td>
                <a title="编辑"   onclick="a(this,'${video.videoid}')" href="javascript:(0);" >  
                 <i class="layui-icon">&#xe60a;</i>
                   <span>编辑<span/> 
                </a>
                  <a title="发布"   onclick="uploadvideo(this,'${video.videoid}')" href="javascript:(0);" >  
                 <i class="layui-icon">&#xe609;</i>
                 <span>发布<span/> 
                </a>
              	<a title="删除" onclick="deleteVideo(this,'${video.videoid}')" href="javascript:(0);">
                    <i class="layui-icon">&#xe640;</i>
                    <span>删除<span/> 
                </a>
                <a title="阅览" onclick="showvideo(this,'${video.videoid}')" href="javascript:(0);">
                    <i class="layui-icon">&#xe64c;</i>
                    <span>阅览<span/> 
                </a>   
                    <a title="设置推荐" onclick="settingRecommend(this,'${video.targeturl}')" href="javascript:(0);">
                    <i class="layui-icon">&#xe640;</i>
                    <span>推荐<span/> 
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

   <script >
   function settingRecommend(obj,targeturl){
	   console.log(targeturl);
	   if(targeturl==""){
			layer.msg('请先发布视频',{icon:0,time:3000});
		   return false;
		   
	   }
		layer.open({
		       type:2,
		       title:"设置推荐",
		       skin:"",
		       area:['900px', '900px'],
		       anim:2,
		      content:"settingRecommend?targeturl="+targeturl,
		   });    
		}
   function a(obj,videoid){
	layer.open({
	       type:2,
	       title:"修改视频信息",
	       skin:"",
	       area:['900px', '800px'],
	       anim:2,
	      content:"findVideoById?videoid="+videoid,
	   });    
	}
   
   function uploadvideo(obj,videoid){
		layer.open({
		       type:2,
		       title:"发布视频信息",
		       skin:"",
		       area:['900px', '800px'],
		       anim:2,
		      content:"uploadVideoIndex?videoid="+videoid,
		   });    
		}
   function showvideo(obj,videoid){
	   var videoid=videoid;
	   var videooriginurl="https://xiaoliwaer.top:525/video/"+videoid+".mp4"
	   $.ajax({
	        url: '/students323/testurl',
	        data:"{\"videooriginurl\":\""+videooriginurl+"\"}",
	     	dataType:'json',//服务器返回json格式数据
			type:'post',//HTTP请求类型
			timeout:10000,//超时时间设置为10秒；
			contentType:'application/json;charset=UTF-8',
	        success:function(data){
	        	if(data.result=="ok"){
	        	console.log(JSON.stringify(data));
	        	
                    layer.msg('链接有效',{icon:1,time:3000});
                    var url=data.url;
                    layer.open({
         		       type:2,
         		       title:"阅览视频",
         		       skin:"",
         		       area:['900px', '800px'],
         		       anim:2,
         		      content:url,
         		   });    
	        	}
	        	if(data.result=="false"){
	        	layer.msg('请先发布视频',{icon:0,time:3000});
	        	}
	        	},
	        error:function(){
	        	 layer.msg('出错',{icon:0,time:3000});
	        }
	    }); 
		
		}
   
   function deleteVideo(obj,videoid){
	   var videoid=videoid;
	   console.log(videoid);
	   $.ajax({
	        url: '/students323/deleteVideo',
	        data:"{\"videoid\":\""+videoid+"\"}",
	    	dataType:'json',//服务器返回json格式数据
			type:'post',//HTTP请求类型
			timeout:10000,//超时时间设置为10秒；
			contentType:'application/json;charset=UTF-8',
	        success:function(data){
	        	if(data.status=="ok"){
				console.log(JSON.stringify(data));
	        	layer.msg('删除成功!',{icon:1,time:2000});
                setTimeout(function () {window.location.href='findVideo';},2000);}       
	        	},
	        error:function(){
	        	 layer.msg('删除失败',{icon:0,time:3000});
	        }
	    }); 
		
		}
   </script>

    


</body>


</html>