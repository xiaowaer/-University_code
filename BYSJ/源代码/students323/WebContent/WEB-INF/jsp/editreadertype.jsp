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
    <form class="layui-form" id="/updatereadertype"  id="f_auto" accept-charset="UTF-8" >
       
       
        <div class="layui-form-item">
            <label for="readerid" class="layui-form-label" >
                <span class="f_sp" >读者id:${readertype.readerid} </span>
            </label>
             <input type="hidden" value="${readertype.readerid}" name="readerid" id="readerid"/>
        </div>
        

        <div class="layui-form-item">
            <label for="v_picsname" class="layui-form-label">
                <span class="f_sp">读者类型</span>
            </label>

           <input type="text" id="readertype" name="readertype"
                       autocomplete="off" value="${readertype.readertype}" class="layui-input">
           
        </div>

           <div class="layui-form-item">
            <label for="v_picskeyword" class="layui-form-label">
                <span class="f_sp">读者等级</span>
            </label>
            
                <input type="text" id="level" name="level"
                       autocomplete="off" value="${readertype.level}" class="layui-input">
            
        </div>
        
            <div class="layui-form-item">
            <label for="v_picsheadurl" class="layui-form-label">
                <span class="f_sp">读者特权级</span>
            </label>
           
                <input type="text" id="priority" name="priority"
                       autocomplete="off" value="${readertype.priority}" class="layui-input">
           
        </div>


        <div class="layui-form-item" id="btn_xg">
            <button  class="layui-btn"  id="btn_on" lay-filter="updatepicsData" lay-submit="">
                修改
           </button>  
 
      </div>
            
    </form>
          
   
</div>

<script>

layui.use(['jquery','form','layer'], function(){
    var form = layui.form,
        $ = layui.jquery;
       
form.on('submit(updatepicsData)', function(data) {									
    // layer.msg('aaa',{icon:1,time:3000});
    var param=data.field;
    console.log(JSON.stringify(param));
    var readerid=param.readerid;
    var readertype=param.readertype;
    var level=param.level;
    var priority=param.priority;
    if(readertype== ""||level==""||priority==""){
    	layer.msg('保持修改内容非空',{icon:0,time:2000});
    	return false;
    }

    
	    $.ajax({
	        url: '/students323/updateReadertype',
	        data:"{\"readerid\":\""+readerid+"\",\"readertype\":\""+readertype+"\",\"level\":\""+level+"\",\"priority\":\""+priority+"\"}",
			dataType:'json',//服务器返回json格式数据
			type:'post',//HTTP请求类型
			timeout:10000,//超时时间设置为10秒；
			headers:{'Content-Type':'application/json'},
	        success:function(data){
	        	if(data.result=="ok"){
	        	console.log(JSON.stringify(data));
	        	var index=parent.layer.getFrameIndex(window.name); //获取当前窗口的name
	               parent.layer.close(index);		//关闭窗口
	        	}        
	        },
	        error:function(){
	        	var index=parent.layer.getFrameIndex(window.name); //获取当前窗口的name
	               parent.layer.close(index);	
	            layer.msg('修改失败',{icon:0,time:3000});
	        }
	    });
	    
   
});
});


   
</script>
</body>
</html>