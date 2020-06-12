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

        .layui-form-item label span{  
               font-size: 30px;
    				padding: 9px 15px;
    				width: 180px;
				    line-height: 20px;
                    text-align: center;
            }
            
        .layui-form .layui-form-item label {
              	   font-size: 30px; 
    				padding: 9px 15px;
    				width: 100%;
				    line-height: 20px;
                    text-align: center;
            }
       
   	.layui-form-item input{
   			font-size: 18px;
               height: 45px;
               width:650px;
              margin:0  auto;
               
            }       
.layui-form-select{ 
    margin:0  auto;    
    max-width: 650px;
}
         dd {
   			font-size: 18px;
               height: 45px;
               width:630px;
              margin:0  auto;
               
            }

.layui-btn {
    display: inline-block;
    height: 60px;
    width:120px;
    margin-left:300px;  
    line-height: 38px;
    background-color: #009688;
    color: #fff;
    white-space: nowrap;
    text-align: center;
    font-size: 38px;
    border: none;
    border-radius: 10px;
    cursor: pointer;
}
    </style>
        <script type="text/javascript" >
 
        </script>
    

</head>

<body>

<div class="x-body">
    <form class="layui-form" id="/updateVideo"  id="f_auto" accept-charset="UTF-8" >
        <input type="hidden" value="${sessionScope.video.videoid}" name="videoid" id="videoid"/>
        <div class="layui-form-item">
            <label for="v_videoid" class="layui-form-label">
                <span class="f_sp">ID:${sessionScope.video.videoid} 视频来源:${sessionScope.video.videoorigin}</span>
            </label>
        </div>
        

        <div class="layui-form-item">
            <label for="v_videoname" class="layui-form-label">
                <span class="f_sp">视频名称</span>
            </label>

           <input type="text" id="v_videoname" name="v_videoname"
                       autocomplete="off" value="${sessionScope.video.videoname}" class="layui-input">
           
        </div>

           <div class="layui-form-item">
            <label for="v_videokeyword" class="layui-form-label">
                <span class="f_sp">关键词</span>
            </label>
            
                <input type="text" id="v_videokeyword" name="v_videokeyword"
                       autocomplete="off" value="${sessionScope.video.videokeyword}" class="layui-input">
            
        </div>
        
            <div class="layui-form-item">
            <label for="v_videoheadurl" class="layui-form-label">
                <span class="f_sp">视频首图</span>
            </label>
           
                <input type="text" id="videoheadurl" name="v_videoheadurl"
                       autocomplete="off" value="${sessionScope.video.videoheadurl}" class="layui-input">
           
        </div>

                             <div class="layui-form-item">
							    <label class="layui-form-label">选择框</label>
							    <div class="layui-form-item">
							      <select class="select" id="fieldSelect" name="fieldSelect" lay-verify="required" >  
							        <c:set var="scid" scope="session" value="${sessionScope.video.cid}"/>
							         <c:forEach items="${vcate}" var="vcate">
							         <c:set var="Vcid"  value="${vcate.cid}"/>
							         <c:choose> 
							        <c:when test="${scid == Vcid}">
       								<option value="${vcate.cid}" selected="selected">${vcate.chname}</option>   
									</c:when> 
									<c:otherwise>  
                   					<option value="${vcate.cid}" >${vcate.chname}</option>   
                   				 </c:otherwise>  
									</c:choose> 
           
               						 </c:forEach>

							      </select>
							     </div>
							  </div>

                    
                    

      
        <div class="layui-form-item" id="btn_xg">
            <button  class="layui-btn"  id="btn_on" lay-filter="updateVideoData" lay-submit="">
                修改
           </button>  
 
      </div>
            
    </form>
          
   
</div>

 <%--添加模态框--%>
    <div class="layui-row" id="test" style="display: none;">
        <div class="layui-col-md10">
            <form class="layui-form" id="publishvideo">
            
               <div class="layui-form-item" style="margin-top:20px;">
                    <label  class="layui-form-label">发布地址：${sessionScope.video.videoheadurl}</label>
 
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo"style ="width:180px;" >提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary"style ="width:180px;">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>



<script>

layui.use(['jquery','form','layer'], function(){
    var form = layui.form,
        $ = layui.jquery;
       
form.on('submit(updateVideoData)', function(data) {									
    // layer.msg('aaa',{icon:1,time:3000});
    var param=data.field;
    console.log(JSON.stringify(param));
    var videoid=param.videoid;
    var videoname=param.v_videoname;
    var videokeyword=param.v_videokeyword;
    var cid = param.fieldSelect;
    if(videoname== ""||videokeyword==""){
    	layer.msg('保持修改内容非空',{icon:0,time:2000});
    	return false;
    }
    console.log(videoname);
    console.log(videokeyword);
    console.log(cid);
    console.log(videoid);
    
	    $.ajax({
	        url: '/students323/updateVideo',
	        data:"{\"videoid\":\""+videoid+"\",\"videoname\":\""+videoname+"\",\"videokeyword\":\""+videokeyword+"\",\"cid\":\""+cid+"\"}",
			dataType:'json',//服务器返回json格式数据
			type:'post',//HTTP请求类型
			timeout:10000,//超时时间设置为10秒；
			headers:{'Content-Type':'application/json'},
	        success:function(data){
	        	if(data.result=="ok"){
	        	console.log(JSON.stringify(data));
	        		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.location.reload();//刷新父页面，注意一定要在关闭当前iframe层之前执行刷新
                     parent.layer.close(index); //再执行关闭
                     alert("修改成功");
	        	}
	        	
	                
	        },
	        error:function(){
	            layer.msg('修改失败',{icon:0,time:3000});
	            setTimeout(function () {window.location.href='findVideo';},2000);
	        }
	    });
	    
   
});
});

/* 
function (obj,videoid){
	   layer.open({
	       type:2,
	       title:"修改视频信息",
	       skin:"",
	       area:['900px', '800px'],
	       anim:2,
	      content:"publishvideo?videoid="+videoid,
	   });
	} */
	



    // layui.use(['form','layer','laydate'], function(){
    //     var form = layui.form,
    //         $ = layui.jquery,
    //         laydate = layui.laydate;
    //     form.on('submit(updateForm)', function(obj,s_id) {
    //
    //         $.ajax({
    //             url: '/updateStudent',
    //             type: "post",
    //             data:{"s_id":s_id},
    //             success:function(data){
    //                 layer.msg('修改成功', {icon: 1, time: 3000});
    //                 setTimeout(function () {window.location.href='/findStudent';},2000);
    //
    //             },
    //             error:function(){
    //                 console.log($("#s_id").val());
    //                 layer.msg('修改失败',{icon:0,time:3000});
    //                 setTimeout(function () {window.location.href='/findStudent';},2000);
    //             }
    //         });
    //     })
    //
    // });

</script>
</body>
</html>