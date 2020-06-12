<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>设置推荐</title>
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
    <form class="layui-form" id="updateRecommend"  id="f_auto" accept-charset="UTF-8" >
        <input type="hidden" value="${targeturl}" name="targeturl" id="targeturl"/>
        <div class="layui-form-item">
            <label for="v_picsid" class="layui-form-label">
                <span class="f_sp">targeturl:${targeturl} </span>
            </label>
        </div>


           <div class="layui-form-item">
            <label for="re_intro" class="layui-form-label">
                <span class="f_sp">推荐描述</span>
            </label>
            
                <input type="text" id="re_intro" name="re_intro"
                       autocomplete="off" value="${recommend.re_intro}" class="layui-input">
            
        </div>
        

                             <div class="layui-form-item">
							    <label class="layui-form-label">选择推荐用户类型</label>
							    <div class="layui-form-item">
							      <select class="select downlist" id="fieldSelect" placeholder="选择"  name="fieldSelect" lay-verify="required" multiple="multiple" >  
							        <c:set var="srt" scope="session" value="TFF"/>
							         <c:forEach items="${readertype}" var="readertype">
							         <c:set var="rt"  value="${readertype.readertype}"/>
							         <c:choose> 
							        <c:when test="${srt == rt}">
       								<option value="${readertype.readertype}" selected="selected">${readertype.readertype}</option>   
									</c:when> 
									<c:otherwise>  
                   					<option value="${readertype.readertype}" >${readertype.readertype}</option>   
                   				 </c:otherwise>  
									</c:choose> 
           
               						 </c:forEach>

							      </select>
							     </div>
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
  
    $(function(){
		//遍历渲染
		$("select.downlist").each(function(index,item) {
			var $this=$(this);
			var $select=$this.next(".layui-form-select");
			$select.addClass("downpanel");
			var $dl=$select.find("dl");
			$(".layui-select-title input",$select).val($this.attr("placeholder"));
			$dl.empty();
			var str="";
			$("option",$this).each(function() {
				str=["<dd>","<input type='checkbox' id='cherkboxtrue' name='",$(this).val(),"' lay-skin='primary' title='",$(this).text(),"' value='true'>","</dd>"].join("");
				$dl.append(str);
			});
			form.render("checkbox");

		})
		
		$(".downpanel").on("click",".layui-select-title",function(e){
			var $select=$(this).parents(".layui-form-select");
			$(".layui-form-select").not($select).removeClass("layui-form-selected");
			$select.addClass("layui-form-selected");
			e.stopPropagation();
		}).on("click",".layui-form-checkbox",function(e){
			e.stopPropagation();
		});
	});
        
form.on('submit(updatepicsData)', function(data) {									
    var param=data.field;
    var cherkboxtrue=$("div.layui-form-checked span");
    var readertype='';
     for(i=0;i<cherkboxtrue.length;i++){
    	var re=cherkboxtrue[i].innerText;
    	readertype=readertype+re+"&";
    	 console.log(re); 
    }
     
    console.log(cherkboxtrue.length); 
    console.log(readertype); 
    console.log(JSON.stringify(param));
    var re_intro=param.re_intro;
    var targeturl=param.targeturl;
  
    
    if(re_intro==""){
    	layer.msg('保持修改内容非空',{icon:0,time:2000});
    	return false;
    }

    
	    $.ajax({
	        url: '/students323/updateRecommend',
	        data:"{\"re_intro\":\""+re_intro+"\",\"readertype\":\""+readertype+"\",\"targeturl\":\""+targeturl+"\"}",
			dataType:'json',//服务器返回json格式数据
			type:'post',//HTTP请求类型
			timeout:10000,//超时时间设置为10秒；
			headers:{'Content-Type':'application/json'},
	        success:function(data){
	        	console.log(JSON.stringify(data));
	        	if(data.result=="ok"){
                  alert("修改成功");
                  var index=parent.layer.getFrameIndex(window.name); //获取当前窗口的name
	               parent.layer.close(index);
	        	}
	        	
	                
	        },
	        error:function(){
	            var index=parent.layer.getFrameIndex(window.name); //获取当前窗口的name
	               parent.layer.close(index);		//关闭窗口
	               layer.msg('修改失败',{icon:0,time:3000});
	        }
	    });
	    
   
});
});


   
</script>
</body>
</html>