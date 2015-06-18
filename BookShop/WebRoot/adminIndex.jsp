<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@page import="com.scnu.po.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<% 
   	String userName="";
   	if(session.getAttribute("user")!=null){
		User user=(User)session.getAttribute("user");
		userName=user.getUserName();
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>网上购书后台管理</title>
   <!-- Bootstrap -->
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.4-dist/css/bootstrap.min.css">
    <link href="css/login.css" rel="stylesheet">
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <style type="text/css">
    	body{
    		padding-top:50px;
    	}
    </style>
  </head>
  <!--导航条-->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation" id="menu-nav">
  <div class="navbar-header">
     　<!-- .navbar-toggle样式用于toggle收缩的内容，即nav-collapse collapse样式所在元素 -->
       <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
         <span class="sr-only">切换导航</span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
       </button>
       <!-- 确保无论是宽屏还是窄屏，navbar-brand都显示 -->
       <a href="adminIndex.jsp" class="navbar-brand">网上购书后台管理系统</a>
       
  </div>
  <!-- 屏幕宽度小于768px时，div.navbar-responsive-collapse容器里的内容都会隐藏，显示icon-bar图标，当点击icon-bar图标时，再展开。屏幕大于768px时，默认显示。 -->
 <div class="navbar-collapse collapse">
    	<ul class="nav navbar-nav">
      		<li class="active"><a href="adminIndex.jsp">首&nbsp;&nbsp;&nbsp;页</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">用户管理 <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                    	<li><a href="servlet/UserServlet?action=show&authority=0">会员账号管理</a></li>
                    	<li class="divider"></li>
        				<li><a href="servlet/UserServlet?action=show&authority=1">系统管理员账号管理</a></li>
                    </ul>
                </li>
      		<li><a href="servlet/BookServlet?action=show">图书管理</a></li>
      		<li><a href="##">查看订单</a></li>
      		<li><a href="admin/Online.jsp">在线用户</a></li>
	 	</ul>
	 	<ul class="nav navbar-nav navbar-right ">
	 		<li><span class="navbar-text"><font color="#cccccc">欢迎您:<b><%=userName%></b></font></span></li>
	 		<li><a href="servlet/LoginOut">退出&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
	 	</ul>
  </div>
</div>
<body>
   <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>欢迎使用</h1>
        <p>这是一个简单的网上购书小网站的后台管理系统，通过这个系统，管理员可以对用户、订单、图书等信息进行管理</p>
        <p><a class="btn btn-primary btn-lg" href="adminIndex.jsp" role="button">Learn more &raquo;</a></p>
      </div>
    </div>
    
	  <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-3">
          <h2>用户管理</h2>
          <p>管理员可以点击用户管理功能对会员用户和系统管理员账号进行管理</p>
          <p>The administrator can click the user management function to manage the members of the user and the system administrator account</p>
          <p><a class="btn btn-default" href="servlet/UserServlet?action=show&authority=0" role="button">View details &raquo;</a></p>
        </div>
        <div class="col-md-3">
          <h2>图书信息</h2>
          <p>系统管理员可以进行用户管理（浏览或更新、删除用户资料）</p>
          <p>System administrators can manage users (browse or update, delete user information)</p>
          <p><a class="btn btn-default" href="servlet/BookServlet?action=show" role="button">View details &raquo;</a></p>
       </div>
        <div class="col-md-3">
          <h2>查看订单</h2>
          <p>系统管理员可以查看所有当前定单（包括已结和未结帐）</p>
          <p>The system administrator can view all the orders (including node and check)</p>
		<p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div>
        <div class="col-md-3">
          <h2>在线用户</h2>
          <p>系统管理员可以查看在线用户</p>
          <p>The system administrator can view online users</p>
          <p><a class="btn btn-default" href="admin/Online.jsp" role="button">View details &raquo;</a></p>
        </div>
      </div>
      <hr>
      <footer>
        <p>&copy; SCNU  2015</p>
      </footer>
    </div> <!-- /container -->
	<script src="bootstrap-3.3.4-dist/js/bootstrap.js"></script>
</body>
</html>
