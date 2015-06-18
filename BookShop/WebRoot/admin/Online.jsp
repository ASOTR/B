<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="java.sql.*,com.scnu.util.*,com.scnu.po.User" %>
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
    		padding-top:100px;
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
      		<li><a href="adminIndex.jsp">首&nbsp;&nbsp;&nbsp;页</a></li>
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
      		<li class="active"><a href="admin/Online.jsp">在线用户</a></li>
	 	</ul>
	 	<ul class="nav navbar-nav navbar-right ">
	 		<li><span class="navbar-text"><font color="#cccccc">欢迎您:<b><%=userName%></b></font></span></li>
	 		<li><a href="servlet/LoginOut">退出&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
	 	</ul>
  </div>
</div>
  <body>
    <div class="table-responsive col-md-offset-2 col-md-9">
 	<table class="table table-striped table-bordered table-hover">
 	<caption>在线用户</caption>
	  	<tr>
			<th>sessionId</th>
			<th>用户名</th>
			<th>ip</th>
			<th>当前访问页面</th>
			<th>最近访问时间</th>
		</tr>
   <%
   		DBHelper db=new DBHelper();
   		Object args[]={};
   		ResultSet rs=db.query("select * from online_inf", args);
   		while(rs.next()){
   			   %>
   			<tr>
   			<td><%=rs.getString(1) %></td>
   			<td><%=rs.getString(2) %></td>
   			<td><%=rs.getString(3) %></td>
   			<td><%=rs.getString(4) %></td>
   			<td><%=new java.util.Date(rs.getLong(5)) %></td>
   			</tr>    
   	<% }%>		   
   </table>
   </div>
   <script src="bootstrap-3.3.4-dist/js/bootstrap.js"></script>
  </body>
</html>
