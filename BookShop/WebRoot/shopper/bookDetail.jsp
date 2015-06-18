<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@page import="com.scnu.po.User,com.scnu.po.Book" %>
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
    <title>网上购书商城</title>
   <!-- Bootstrap -->
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.4-dist/css/bootstrap.min.css">

    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <style type="text/css">
    	body{
    		padding-top:50px;
    	}
    	.row{
    		margin-top:50px;
    		padding-left:50px;
    		padding-right:50px;
    	}
    	.row .rowleft{
    		background-color:#eeeeee;
    	}
    	.row .rowright{
    		margin-left:20px;
    		background-color:#ffffff;
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
       <a href="index.jsp" class="navbar-brand"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;欢迎来到网上购书商城</a>
       
  </div>
  <!-- 屏幕宽度小于768px时，div.navbar-responsive-collapse容器里的内容都会隐藏，显示icon-bar图标，当点击icon-bar图标时，再展开。屏幕大于768px时，默认显示。 -->
 <div class="navbar-collapse collapse">
    	<ul class="nav navbar-nav">
    	<%
    		if(userName.equals("")){
    			out.write("<li><a href='customerLogin.jsp'><span class='glyphicon glyphicon-user' aria-hidden='true'></span>&nbsp;&nbsp;[登录]&nbsp;&nbsp;&nbsp;&nbsp;</a></li>");
    		}else{//用户已登录，显示欢迎xxx信息
    		out.write("<li><span class='navbar-text'><font color='#cccccc'>&nbsp;&nbsp;&nbsp;&nbsp;Hi~:<b>"+userName+"</b></font></span></li><li><a href='servlet/CustomerLoginOut'>[退出]&nbsp;&nbsp;&nbsp;&nbsp;</a></li>");
    		}
    	 %>
	 	</ul>
	 	<ul class="nav navbar-nav navbar-right ">
	 	    <li><a href="##"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>&nbsp;&nbsp;购物车<span class="badge">42</span></a></li>
      		<li><a href="##"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;我的订单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
	 	</ul>
  </div>
</div>
  <body>
    <ol class="breadcrumb">
	  <li><a href="index.jsp">首页</a></li>
	  <li class="active">商品详情</li>
	</ol>
<div class="row">
<div class="rowleft col-sm-2 col-md-2">
<h3>&nbsp;&nbsp;浏览过的商品</h3>	
    <div class="thumbnail">
      <img src="image/santi.png" alt="三体">
      <div class="caption">
        <h3>三体 <span class="small">三体</span></h3>
        <p><span class="label label-danger">￥22</span></p>
      </div>
    </div>
    <div class="thumbnail">
      <img src="image/santi.png" alt="三体">
      <div class="caption">
        <h3>三体 <span class="small">三体</span></h3>
        <p><span class="label label-danger">￥22</span></p>
      </div>
  	</div>
</div>

	<%
		Book book=new Book();
		if(request.getAttribute("book")!=null){
			book=(Book)request.getAttribute("book");
		}
	 %>
<div class="rowright col-md-9">
	 <div class="col-xs-6 col-md-6">
	    <a href="servlet/BookDetail?id=<%=book.getId() %>" class="thumbnail">
	      <img src="<%=book.getImgUrl() %>" alt="<%=book.getBookName() %>">
	    </a>
  	</div>
  	<div class="col-xs-6 col-md-6">
	    <h3><b><%=book.getBookName() %></b></h3>
	    <p><b>描述：</b><%=book.getBook_Description() %></p>
	    <p><b>价格：<span class="label label-danger">￥<%=book.getPrice() %></span></b></p>
	    <p><b>出版社：</b><%=book.getPublishing_Company() %></p>
	    <p><b>出版时间：</b><%=book.getDate() %></p>
	    <p><b>IBSN：</b><%=book.getISBN() %></p>
	    <hr>
	    <div class="col-md-12">
	    <div class="col-md-3">我要买</div>
		<div class="col-md-4">
		    <div class="input-group">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="button">-</button> 
		      </span>
		      <input type="text" class="form-control" value="1">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="button">+</button> 
		      </span>
		    </div><!-- /input-group -->
		  </div><!-- /.col-lg-6 -->
		 <div class="col-md-2">件</div>
	    </div>
	    <br><br><br><br><br>
	    <div class="col-md-offset-4">
	    <button class="btn btn-warning" type="button"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>&nbsp;&nbsp;| &nbsp;加入购物车</button>
	    </div>
  	</div>
</div>
</div>
  </body>
</html>
