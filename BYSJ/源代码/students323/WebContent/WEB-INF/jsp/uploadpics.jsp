<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta charset="utf-8">
  <title>upload</title>
   <link rel="stylesheet" href="lib/layui/css/layui.css">
    <script type="text/javascript" src="./js/jquery-1.3.2.min.js"></script>
    <script src="lib/layui/layui.js"></script>
    <!-- 配置文件 -->
    <script type="text/javascript" src="lib/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="lib/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="lib/ueditor/ueditor.parse.js"></script>
    
    
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


.layui-btn {
    display: inline-block;
    height: 60px;
    width:120px;
    margin-left:300px;  
    line-height: 38px;
    background-color: #90CAF9;
    color: #fff;
    white-space: nowrap;
    text-align: center;
    font-size: 22px;
    border: none;
    border-radius: 10px;
    cursor: pointer;
}

p{
margin:0px auto;
font-size:20px;
text-align:center;
word-wrap:break-word;
}
.dragkuang{
margin-left:110px;

}


    </style>
</head>

<body>
	<div class="layui-form">
		 <div class="layui-form-item">
            <label for="v_picsid" class="layui-form-label">
                <span class="f_sp" >ID:${pics.picsid} 图册来源:${pics.picsorigin}</span>
           		<span id="picsidflag" style="display:none;" >${pics.picsid} </span>
            </label>
        </div>
           <div class="layui-form-item">
            <label for="v_picsname" class="layui-form-label">
                <span class="f_sp">图册标题</span>
            </label>  
            <div class="layui-form-item">
	 <div class="layui-form-item">
                <p class="f_sp" style="font-size:20px;">${pics.picsname}</p>
        </div>
        <div class="layui-form-item">
            <label for="v_picsid" class="layui-form-label">
                <span class="f_sp">爬取时间:${pics.picsctime} </span>
            </label>
        </div>
	   <div class="layui-form-item">
            <label for="v_picsname" class="layui-form-label">
                <span class="f_sp">图册url</span>
            </label>
            
            <div class="layui-form-item">
            	<p id="thispicsurl">${pics.picsoriginurl}</p>
            
        </div>
		<span id="picscontent" >${picscontent}</span>
        <div class="layui-form-item layui-col-lg6">
            <label class="layui-form-label">编辑图册</label>
                        <div class="layui-input-block">
                            <textarea id="uditcontent" name="desc" placeholder="请输入内容"  style ="height:300px;width:700px"></textarea>    
                        </div>
                    </div>
            
             
                
                <div class="layui-inline">
                        <button  class="layui-btn"   id="uploadpicscontent" lay-submit="">
                 发布图册
           </button>  
                              
         <button  class="layui-btn"  id="btn_on" lay-filter="testurl" lay-submit="">
                 测试URL
           </button>  
            
                    
                </div>
            </div>
    </div>


  <script type="text/javascript" >
  layui.use(['form', 'element', 'layer', 'jquery'], function () {
	  var form = layui.form, $ = layui.jquery, element = layui.element, layer = layui.layer;
          var ue =  UE.getEditor('uditcontent');
     var picscontent =<%=request.getAttribute("data")%>;;
          ue.ready(function () {
              ue.execCommand('insertHtml', picscontent.picscontent);
          })  
         
          
        $("#uploadpicscontent").click( function() {									
    	 var picscontent=UE.getEditor('uditcontent').getContent();
    	 var picsid=$('#picsidflag').text();
     	 var picsoriginurl= $('#thispicsurl').text();
     		console.log(typeof(picscontent));
			console.log(picsoriginurl);
    	 			console.log(picscontent);
    	 			console.log(picsid);
    	 			var data = [];var data1 = [];
    				var picscontentobject = new Object();
    				picscontentobject.picscontent=picscontent;
    				picscontentobject.picsoriginurl=picsoriginurl;
    				picscontentobject.picscontent=picscontent;
    				data.push(picscontentobject)
    				var picscontentobject1=new Object();
    				picscontentobject1.data=data;
    				picscontentobject1.picsid=picsid;
    				picscontentobject1.picsoriginurl=picsoriginurl;
    				data1.push(picscontentobject1);
    			
    				

    		     $.ajax({
    		        url: '/students323/uploadpicscontent',
    		        data:JSON.stringify(data1),
    				dataType:'json',//服务器返回json格式数据
    				type:'post',//HTTP请求类型
    				timeout:10000,//超时时间设置为10秒；
    				headers:{'Content-Type':'application/json'},
    		        success:function(data){
    		        	if(data.result=="ok"){
    		        	console.log(JSON.stringify(data));
    		         
    	                     layer.msg('修改有效',{icon:1,time:3000});
    		        	}else{
    		        		 layer.msg('修改无效',{icon:0,time:3000});
    		        	}        	
    		                
    		        },
    		        error:function(){
    		        	 layer.msg('修改无效',{icon:0,time:3000});
    		        }
    		    }); 
    		   
    	   
    	});
          
      form.on('submit(testurl)', function() {									
    	 var picsoriginurltmep= $('#thispicsurl').text();
    	 var picsoriginurl=picsoriginurltmep+"?vt=4&hd=1";
    	 console.log(picsoriginurl);
    	 			
    		     $.ajax({
    		        url: '/students323/testurlpics',
    		        data:"{\"picsoriginurl\":\""+picsoriginurl+"\"}",
    				dataType:'json',//服务器返回json格式数据
    				type:'post',//HTTP请求类型
    				timeout:10000,//超时时间设置为10秒；
    				headers:{'Content-Type':'application/json'},
    		        success:function(data){
    		        	if(data.result=="ok"){
    		        	console.log(JSON.stringify(data));
    	                     layer.msg('链接有效',{icon:1,time:3000});
    	                     window.open (picsoriginurl , "_blank" ) ;
    		        	}else{
    		        		 layer.msg('链接无效',{icon:0,time:3000});
    		        	}        	
    		                
    		        },
    		        error:function(){
    		        	 layer.msg('链接无效',{icon:0,time:3000});
    		        }
    		    }); 
    		   
    	   
    	});
      
      
});

        </script>

</body>
</html>