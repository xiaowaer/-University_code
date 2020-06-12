<%--
  Created by IntelliJ IDEA.
  User: hkw
  Date: 2018/10/15
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台登录</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="css/font.css">
    <link rel="icon" href="images/favicon.ico" sizes="32x32" />
    <link rel="stylesheet" href="css/xadmin.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script src="lib/layui/layui.js"></script>
   <script type="text/javascript" src="js/xadmin.js"></script>

    <style>
       
        .o_span{
            display: block;
            text-align: center;
            font-size: 20px;
            letter-spacing:8px
        }
    </style>
</head>
<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a>资讯新闻阅读APP后台管理系统</a></div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>
  
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
             <a href="javascript:;"style="font-size: 25px;">欢迎管理员:${sessionScope.ad.nick}</a> 
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a onclick="x_admin_show('切换帐号','loginOut')">切换帐号</a></dd>
                <dd><a href="/loginOut">退出</a></dd>
            </dl>
        </li>
        
    </ul>

</div>

<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>读者管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="findReader">
                            <%--点击在右侧出现动态的Tab--%>
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>读者信息</cite>

                        </a>
                    </li >

                </ul>
            </li>

            <li>
                <a href="javascript:;">
                     <i class="layui-icon">&#xe705;</i>
                    <cite>新闻管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="findNews">
                            <%--点击在右侧出现动态的Tab--%>
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>新闻列表</cite>
                        </a>
                    </li >
                   
                </ul>
                
                   <ul class="sub-menu">
                    <li>
                        <a _href="insertNewsIndex">
                            <%--点击在右侧出现动态的Tab--%>
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>自定义新闻</cite>
                        </a>
                    </li >
                </ul>
            </li>


            <li>
                <a href="javascript:;">
                    <i class="layui-icon">&#xe6ed;</i>
                    <cite>视频管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
               
                <ul class="sub-menu">
                    <li>
                        <a _href="findVideo">
                            <%--点击在右侧出现动态的Tab--%>
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>视频列表</cite>
                        </a>
                    </li >
                </ul>
                        <ul class="sub-menu">
                    <li>
                        <a _href="insertVideoIndex">
                            <%--点击在右侧出现动态的Tab--%>
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>自定义视频</cite>
                        </a>
                    </li >
                </ul>
            </li>

               

            </li>

            <li>
                <a href="javascript:;">
                    <i class="layui-icon">&#xe64a;</i>
                    <cite>图册管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>

                <ul class="sub-menu">
                    <li>
                        <a _href="findPics">
                            <%--点击在右侧出现动态的Tab--%>
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>图册列表</cite>
                        </a>
                    </li >
                </ul>
         
                 <ul class="sub-menu">
                    <li>
                        <a _href="insertPicsIndex">
                            <%--点击在右侧出现动态的Tab--%>
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>自定义图册</cite>
                        </a>
                    </li >
                </ul>
            </li>
            </li>

            <li>
                <a href="javascript:;">
                    <i class="layui-icon">&#xe611;</i>
                    <cite>评论公告管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                
                <ul class="sub-menu">
                    <li>
                        <a _href="findNotice">
                            <%--点击在右侧出现动态的Tab--%>
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>通知列表</cite>
                        </a>
                    </li >
                </ul>
                
                 <ul class="sub-menu">
                    <li>
                        <a _href="findComment">
                            <%--点击在右侧出现动态的Tab--%>
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>评论列表</cite>
                        </a>
                    </li >
                </ul>
                
            </li>
            
             <li>
                <a href="javascript:;">
                    <i class="layui-icon">&#xe60a;</i>
                    <cite>频道管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                
                <ul class="sub-menu">
                    <li>
                        <a _href="findCategories">
                            <%--点击在右侧出现动态的Tab--%>
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>频道列表</cite>
                        </a>
                    </li >
                </ul> 
                 
            </li>

            
           <%--   <li>
                <a href="javascript:;">
                    <i class="layui-icon">&#xe658;</i>
                    <cite>读者活动管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
             
            </li> --%>


            <li>
                <a href="javascript:;">
                <i class="layui-icon">&#xe670;</i>
                    <cite>推荐管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="findRecommend">
                            <%--点击在右侧出现动态的Tab--%>
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>推荐列表</cite>
                        </a>
                    </li >


                </ul>
            </li>


        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
          
           <!--  <li class="home" ><i class="layui-icon">&#xe68e;</i>我的桌面</li>-->
        </ul>
        <div class="layui-tab-content" >
            <!-- <div class="layui-tab-item layui-show o_div" >
             
                <div class="layui-col-md6" style="padding: 30px;left: 60px;background-color: #F2F2F2;">
                <div class="layui-card">
      
                </div> -->
            </div>
        </div>
    </div>
</div>

<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright"></div>
</div>
<!-- 底部结束 -->

</body>
</html>
