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
    line-height: 38px;
    background-color: #009688;
    color: #fff;
    white-space: nowrap;
    text-align: center;
    font-size: 30px;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    margin: 0 auto;
    padding:15px;
}
.layui-upload-list{
 text-align: center;
}
.layui-input-block {
    margin-left: 50px;
    min-height: 36px;
}
.progresstiao{
margin: 0 auto;
width:500px;

}
#prevModal {width:100%; height:100%; text-align:center; display:none;}

    </style>
        <script type="text/javascript" >
 
        </script>
    

</head>

<body>

<div class="x-body">
    <form class="layui-form" id="myvideo"   accept-charset="UTF-8" >
        <input type="hidden" value="${video.videoid}" name="videoid" id="videoid"/>
        <div class="layui-form-item">
            <label for="v_videoid" class="layui-form-label">
               
            </label>
        </div>
        

        <div class="layui-form-item">
            <label for="v_videoname" class="layui-form-label">
                <span class="f_sp">视频名称</span>
            </label>

           <input type="text" id="v_videoname" name="v_videoname"
                       autocomplete="off" value="" class="layui-input">
           
        </div>
        
        
        <div class="layui-form-item">
            <label for="v_videoheadurl" class="layui-form-label">
                <span class="f_sp">视频首图</span>
            </label>

           <input type="text" id="v_videoheadurl" name="v_videoheadurl"
                       autocomplete="off" value="" class="layui-input">
           
        </div>

           <div class="layui-form-item">
            <label for="v_videokeyword" class="layui-form-label">
                <span class="f_sp">关键词</span>
            </label>
            
                <input type="text" id="v_videokeyword" name="v_videokeyword"
                       autocomplete="off" value="" class="layui-input">
            
        </div>

                             <div class="layui-form-item">
							    <label class="layui-form-label">视频分类</label>
							    <div class="layui-form-item">
							      <select class="select" id="fieldSelect" name="fieldSelect" lay-verify="required" >  
							         <c:forEach items="${vcate}" var="vcate">
                   					<option value="${vcate.cid}" >${vcate.chname}</option>   
               						 </c:forEach>
							      </select>
							     </div>
							</div>
							

         <div class="layui-form-item">
            <label class="layui-form-label">
            <span class="f_sp">上传首图</span>
            </label>
            <div class="layui-input-block">
            
                <div class="layui-upload-list">
                	<!-- 文件选择框 -->
                    <div class="layui-upload-drag UploadBusi dragkuang">
                        <div lay-filter="UploadToolsOne">
                            <i class="layui-icon" style="font-size: 50px !important;"></i>
                            <p>点击上传，或将文件拖拽到此处</p>
                        </div>
                        <span id="demo2" class="layui-form-label layui-hide" style="width: auto;"></span>
                        <img id="demo1" src="./images/moren.jpg" class="layui-upload-img layui-hide" style="height: 107px; width: 196px;" />
                    </div>
                    <!-- 进度条样式 -->
                    <div class="layui-progress layui-progress-big progresstiao" lay-showpercent="true" lay-filter="demo" >
                        <div class="layui-progress-bar layui-bg-red" lay-percent="0%"></div>
                    </div>
                       <div class="layui-inline" style="margin-top:30px;">
                 <a id="uploadVideobtn" class="layui-btn">上传</a>  
 				 <a  class="layui-btn"  id="createvideo" onclick="createvideo(this)" href="javascript:(0);" >创建</a> 
				
      </div>
                </div>     
    </form> 
</div>
     


<script type="text/javascript">

function createvideo(obj){
	 	var videoname=$("#v_videoname").attr("value");
	    var videoheadurl=$("#v_videoheadurl").attr("value");
	    var videokeyword=$("#v_videokeyword").attr("value");
	    var cid = $("#fieldSelect").attr("value");
	    if(videoname== ""){
	    	layer.msg('视频标题不能为空',{icon:2,time:2000});
	    	return false;
	    }
	    if(videoheadurl== ""){
	    	layer.msg('视频首图不能为空',{icon:2,time:2000});
	    	return false;
	    }
	    if(videokeyword== ""){
	    	layer.msg('视频关键字不能为空',{icon:2,time:2000});
	    	return false;
	    }
	    console.log(videoname);
	    console.log(videoheadurl);
	    console.log(cid);
	    console.log(videokeyword);
	    

	    $.ajax({
	        url: '/students323/insertvideo',
	        data:"{\"videoname\":\""+videoname+"\",\"videoheadurl\":\""+videoheadurl+"\",\"videokeyword\":\""+videokeyword+"\",\"cid\":\""+cid+"\"}",
			type:'post',//HTTP请求类型
			dataType:'json', 
			timeout:10000,//超时时间设置为10秒；
			contentType:'application/json;charset=UTF-8',
	        success:function(data){
	        	if(data.result=="ok"){
                     alert("创建成功");
	        	}
	        	
	                
	        },
	        error:function(){
	            layer.msg('创建失败',{icon:0,time:3000});
	        }
	    });
	
}


		
layui.use(['form', 'upload', 'element', 'layer', 'jquery'], function () {
	  var form = layui.form, upload = layui.upload, $ = layui.jquery, element = layui.element, layer = layui.layer;

    //创建监听函数
    var xhrOnProgress = function (fun) {
        xhrOnProgress.onprogress = fun; //绑定监听
        //使用闭包实现监听绑
        return function () {
            //通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
            var xhr = $.ajaxSettings.xhr();
            //判断监听函数是否为函数
            if (typeof xhrOnProgress.onprogress !== 'function')
                return xhr;
            //如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
            if (xhrOnProgress.onprogress && xhr.upload) {
                xhr.upload.onprogress = xhrOnProgress.onprogress;
            }
            return xhr;
        }
    }


upload.render({
	elem: '.UploadBusi', // 文件选择
	accept:'images',
	method: 'post',
	url: 'uploadvideoheadurl',
	auto: false, // 设置不自动提交
	multiple: true,
	bindAction: '#uploadVideobtn', // 提交按钮
	xhr: xhrOnProgress,
	progress: function (value) {
      console.log("进度：" + value + '%');
      element.progress('demo', value + '%');
  },
  choose: function (obj) {
      element.progress('demo', '0%');
      //预读本地文件示例，不支持ie8
      var uploadFileInput = $(".layui-upload-file").val();
      var uploadFileName = uploadFileInput.split("\\");
  	//图像预览
		obj.preview(function(index, file, result){
			console.log(result);
			 $('#demo1').attr('src',result); //指定数据源
		});
      $('#demo2').text(uploadFileName[uploadFileName.length - 1]);
      console.log(uploadFileName[uploadFileName.length - 1]);
      $('#demo2').removeClass("layui-hide");
      $('#demo1').removeClass("layui-hide");
      $('div[lay-filter="UploadToolsOne"]').addClass("layui-hide");
  },
  before: function (obj) {
      layer.load();
  }
  , done: function (res, index, upload) {
      //上传完毕回调
      if (res.status == "ok") {
      	layer.closeAll('loading'); //关闭loading
      	console.log(res.url);
      	$("#v_videoheadurl").attr("value",res.url);
   /*    	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
          parent.location.reload();//刷新父页面，注意一定要在关闭当前iframe层之前执行刷新
           parent.layer.close(index); //再执行关闭 */
           alert("修改成功");     
      }
      
  }
  , error: function (res) {
      //请求异常回调
      //layer.msg("系统异常错误！", { icon: 5, time: 2000, shift: 6 });
      element.progress('demo', '0%');
      layer.closeAll('loading'); //关闭loading
  }
});
});

      </script>
</body>
</html>