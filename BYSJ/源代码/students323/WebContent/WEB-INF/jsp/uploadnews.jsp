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
            <label for="v_newsid" class="layui-form-label">
                <span class="f_sp" >ID:${news.newsid} 新闻来源:${news.newsorigin}</span>
           		<span id="newsidflag" style="display:none;" >${news.newsid} </span>
            </label>
        </div>
           <div class="layui-form-item">
            <label for="v_newsname" class="layui-form-label">
                <span class="f_sp">新闻标题</span>
            </label>  
            <div class="layui-form-item">
	 <div class="layui-form-item">
                <p class="f_sp" style="font-size:20px;">${news.newstitle}</p>
        </div>
        <div class="layui-form-item">
            <label for="v_newsid" class="layui-form-label">
                <span class="f_sp">爬取时间:${news.newsctime} </span>
            </label>
        </div>
	   <div class="layui-form-item">
            <label for="v_newsname" class="layui-form-label">
                <span class="f_sp">新闻url</span>
            </label>
            
            <div class="layui-form-item">
            	<p id="thisnewsurl">${news.newsoriginurl}</p>
            
        </div>
		<span id="newscontent" >${newscontent}</span>
        <div class="layui-form-item layui-col-lg6">
            <label class="layui-form-label">编辑新闻</label>
                        <div class="layui-input-block">
                            <textarea id="uditcontent" name="desc" placeholder="请输入内容"  style ="height:300px;width:700px"></textarea>    
                        </div>
                    </div>
            
             
                
                <div class="layui-inline">
                        <button  class="layui-btn"   id="uploadnewscontent" lay-submit="">
                 发布新闻
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
     var newscontent =<%=request.getAttribute("data")%>;
          ue.ready(function () {
              ue.execCommand('insertHtml',newscontent.newscontent);
          })  
          
        $("#uploadnewscontent").click( function() {									
    	 var newscontent=UE.getEditor('uditcontent').getContent();
    	 var newsid=$('#newsidflag').text();
     	 var newsoriginurl= $('#thisnewsurl').text();
     		console.log(typeof(newscontent));
			console.log(newsoriginurl);
    	 			console.log(newscontent);
    	 			console.log(newsid);
    	 			var data = [];var data1 = [];
    				var newscontentobject = new Object();
    				newscontentobject.newscontent=newscontent;
    				newscontentobject.newsoriginurl=newsoriginurl;
    				newscontentobject.newscontent=newscontent;
    				data.push(newscontentobject)
    				var newscontentobject1=new Object();
    				newscontentobject1.data=data;
    				newscontentobject1.newsid=newsid;
    				newscontentobject1.newsoriginurl=newsoriginurl;
    				data1.push(newscontentobject1);
    			
    				

    		     $.ajax({
    		        url: '/students323/uploadNewscontent',
    		        data:JSON.stringify(data1),
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
    		            alert("修改成功！！！！");
    	                    
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
    	 var newsoriginurltmep= $('#thisnewsurl').text();
    	 
    	 var newsoriginurl=newsoriginurltmep;
    	 console.log(newsoriginurl);
    	 			
    		     $.ajax({
    		        url: '/students323/testurlnews',
    		        data:"{\"newsoriginurl\":\""+newsoriginurl+"\"}",
    				dataType:'json',//服务器返回json格式数据
    				type:'post',//HTTP请求类型
    				timeout:10000,//超时时间设置为10秒；
    				headers:{'Content-Type':'application/json'},
    		        success:function(data){
    		        	if(data.result=="ok"){
    		        	console.log(JSON.stringify(data));
    	                     layer.msg('链接有效',{icon:1,time:3000});
    	                     window.open (data.url , "_blank" ) ;
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