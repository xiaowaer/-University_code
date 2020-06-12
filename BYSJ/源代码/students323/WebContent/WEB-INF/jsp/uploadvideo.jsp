<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta charset="utf-8">
  <title>upload</title>
   <link rel="stylesheet" href="lib/layui/css/layui.css">
    <script type="text/javascript" src="./js/jquery-1.3.2.min.js"></script>
    <script src="lib/layui/layui.js"></script>
    <script src="lib/layui/lay/modules/upload.js"></script>
    
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
padding:10px 80px;
text-align:center;
word-wrap:break-word;
}
.dragkuang{
margin-left:110px;

}
.progresstiao{
margin-left:115px;
width:500px;

}


    </style>
</head>

<body>
	<div class="layui-form">
		 <div class="layui-form-item">
            <label for="v_videoid" class="layui-form-label">
                <span class="f_sp" >ID:${sessionScope.video.videoid} 视频来源:${sessionScope.video.videoorigin}</span>
           		<span id="videoidflag" style="display:none;" >${sessionScope.video.videoid} </span>
            </label>
        </div>
           <div class="layui-form-item">
            <label for="v_videoname" class="layui-form-label">
                <span class="f_sp">视频标题</span>
            </label>  
            <div class="layui-form-item">
	 <div class="layui-form-item">
                <p class="f_sp" style="font-size:20px;">${sessionScope.video.videoname}</p>
        </div>
        <div class="layui-form-item">
            <label for="v_videoid" class="layui-form-label">
                <span class="f_sp">爬取时间:${sessionScope.video.videoctime} </span>
            </label>
        </div>
	   <div class="layui-form-item">
            <label for="v_videoname" class="layui-form-label">
                <span class="f_sp">视频url</span>
            </label>
            
            <div class="layui-form-item">
            
            	<p id="thisvideourl" >${sessionScope.video.videooriginurl} </p>
            
        </div>
	
        <div class="layui-form-item layui-col-lg6">
            <label class="layui-form-label">上传文件</label>
            <div class="layui-input-block">
            
                <div class="layui-upload-list">
                	<!-- 文件选择框 -->
                    <div class="layui-upload-drag UploadBusi dragkuang">
                        <div lay-filter="UploadToolsOne">
                            <i class="layui-icon" style="font-size: 50px !important;"></i>
                            <p>点击上传，或将文件拖拽到此处</p>
                        </div>
                        <span id="demo2" class="layui-form-label layui-hide" style="width: auto;"></span>
                        <img id="demo1" class="layui-upload-img layui-hide" style="height: 107px; width: 196px;" />
                    </div>
                    <!-- 进度条样式 -->
                    <div class="layui-progress layui-progress-big progresstiao" lay-showpercent="true" lay-filter="demo" >
                        <div class="layui-progress-bar layui-bg-red" lay-percent="0%"></div>
                    </div>
                </div>
                
                <div class="layui-inline">
                    <input id="uploadVideobtn" type="button" class="layui-btn" value="本地上传"  style="
                     display: inline-block;
    height: 60px;
    width:120px;
    margin-left:200px;  
    line-height: 38px;
    background-color: #009688;
    color: #fff;
    white-space: nowrap;
    text-align: center;
    font-size: 22px;
    border: none;
    border-radius: 10px;
    cursor: pointer;"/>
                              
         <button  class="layui-btn"  id="btn_on" lay-filter="testurl" lay-submit="">
                 测试URL
           </button>  
            
                    
                </div>
            </div>
        </div>
    </div>


  <script type="text/javascript" >
  layui.use(['form', 'upload', 'element', 'layer', 'jquery'], function () {
	  var form = layui.form, upload = layui.upload, $ = layui.jquery, element = layui.element, layer = layui.layer;
      form.on('submit(testurl)', function() {									
    	 var videooriginurl= $('#thisvideourl').text();
    	 			console.log(videooriginurl);
    		     $.ajax({
    		        url: '/students323/testurl',
    		        data:"{\"videooriginurl\":\""+videooriginurl+"\"}",
    				dataType:'json',//服务器返回json格式数据
    				type:'post',//HTTP请求类型
    				timeout:10000,//超时时间设置为10秒；
    				headers:{'Content-Type':'application/json'},
    		        success:function(data){
    		        	if(data.result=="ok"){
    		        	console.log(JSON.stringify(data));     
    	                     layer.msg('链接有效',{icon:1,time:3000});
    	                     window.open (videooriginurl , "_blank" ) ;
    		        	}else{
    		        		 layer.msg('链接无效',{icon:0,time:3000});
    		        	}        	
    		                
    		        },
    		        error:function(){
    		        	 layer.msg('链接无效',{icon:0,time:3000});
    		        }
    		    }); 
    		   
    	   
    	});
      
      

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
	accept:'video',
	method: 'post',
	url: 'uploadVideo',
	auto: false, // 设置不自动提交
	multiple: true,
	bindAction: '#uploadVideobtn', // 提交按钮
	xhr: xhrOnProgress,
	progress: function (value) {
        console.log("进度：" + value + '%');
        element.progress('demo', value + '%');
    }
    , choose: function (obj) {
        element.progress('demo', '0%');
        //预读本地文件示例，不支持ie8
        var uploadFileInput = $(".layui-upload-file").val();
        var uploadFileName = uploadFileInput.split("\\");
        $('#demo2').text(uploadFileName[uploadFileName.length - 1]);
        var videoidflag=$('#videoidflag').text().trim();
        var videostring=videoidflag+".mp4"
        console.log("测试");
        console.log($('#videoidflag').text());
        console.log(videostring);
        console.log(uploadFileName[uploadFileName.length - 1]);
	if(videostring!=uploadFileName[uploadFileName.length - 1]){
	 layer.msg('文件命名有问题',{icon:0,time:3000});
	 return false;
} 
        $('#demo2').removeClass("layui-hide");
        $('div[lay-filter="UploadToolsOne"]').addClass("layui-hide");
        $('#uploadVideobtn').removeClass("layui-hide");
    }
    , before: function (obj) {
        layer.load();
    }
    , done: function (res, index, upload) {
        //上传完毕回调
        if (res.status == "ok") {
        	layer.closeAll('loading'); //关闭loading
        	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.location.reload();//刷新父页面，注意一定要在关闭当前iframe层之前执行刷新
             parent.layer.close(index); //再执行关闭
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
