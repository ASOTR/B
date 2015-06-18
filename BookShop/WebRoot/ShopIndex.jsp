<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@page import="com.scnu.po.User,com.scnu.po.Book" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
   	String userName="";
   	String userNumber="";
   	if(session.getAttribute("user")!=null){
		User user=(User)session.getAttribute("user");
		userName=user.getUserName();
		userNumber=user.getUserNumber();
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
    	.carousel{
    		height:400px;
    		bacground-color:#000;
    	}
    	.carousel .item{
    		height:400px;
    		bacground-color:#000;
    	}
    	.carousel img{
    		width:100%;
    	}
    	.carousel-caption p{
            margin-buttom:20px;
            font-size:20px;
            line-height:1.8;
    	}
    	.row{
    		margin-top:50px;
    		padding-left:50px;
    		padding-right:50px;
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
	 	    <li><a href="##"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>&nbsp;&nbsp;购物车<span class="badge">12</span></a></li>
      		<li><a href="##"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;我的订单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
	 	</ul>
  </div>
</div>
<body>
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <a href="servlet/BookDetail?ISBN=23579654"><img src="image/santi.png" alt="三体"></a>
      <div class="carousel-caption">
        <h1>三体(全三册)</h1>
        <p>（刘慈欣作品，中国科幻文学的里程碑之作！）</p>
        <p><a class="btn btn-lg btn-primary" href="servlet/ShoppingCart?action=add&userNumber=<%=userNumber  %>&ISBN=23579654">点击购买</a></p>
      </div>
    </div>
    <div class="item">
      <a href="servlet/BookDetail?ISBN=9787121242533">
      <img src="image/javaEE.png" alt="三体"></a>
      <div class="carousel-caption">
         <h1>轻量级Java EE企业应用实战（第4版）</h1>
        <p>（Struts 2+Spring 4+Hibernate整合开发(含CD光盘1张)
国家级奖项获奖作品升级版，四版累计印刷27次发行量超10万册的轻量级Java EE经典著作 
        </p>
        <p><a class="btn btn-lg btn-primary" href="servlet/ShoppingCart?action=add&userNumber=<%=userNumber  %>&ISBN=9787121242533">点击购买</a></p>
      </div>
    </div>
      <div class="item">
       <a href="servlet/BookDetail?ISBN=9787807407270">
      <img src="image/daomu.png" alt="三体"></a>
      <div class="carousel-caption">
        <h1>盗墓笔记</h1>
        <p>六周年完美纪念套装（盗墓笔记全套，豪华装帧，极致呈现；独家附赠：《盗墓帝国》公民谱+盗墓之旅卡贴+妖怪明信片）
        </p>
        <p><a class="btn btn-lg btn-primary" href="servlet/ShoppingCart?action=add&userNumber=<%=userNumber  %>&ISBN=9787807407270">点击购买</a></p>
      </div>
    </div>

  </div>
  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>  
<br/><br/>
<h3><span class="glyphicon glyphicon-triangle-right" aria-hidden="true">精选图书</h3>
<hr>
<div class="row">
	<% 
		ArrayList <Book> booklist=new ArrayList<Book>();
		booklist=(ArrayList<Book>)request.getAttribute("booklist");
		int rowNow=(Integer)request.getAttribute("rowNow");
		int pageSize=(Integer)request.getAttribute("pageSize");
		int rowEnd=0;
		if(booklist.size()<(rowNow+pageSize)){//
			rowEnd=booklist.size();
		}else{
			rowEnd=rowNow+pageSize;
		}
		for(int i=rowNow;i<rowEnd;i++){
	%>		
  <div class="col-sm-6 col-md-3">
    <div class="thumbnail">
      <a href="servlet/BookDetail?id=<%=booklist.get(i).getId() %>"><img src="<%=booklist.get(i).getImgUrl() %>" alt="<%=booklist.get(i).getBookName() %>"></a>
      <div class="caption">
        <h3><%=booklist.get(i).getBookName() %></h3>
        <p><%=booklist.get(i).getBook_Description() %></p>
        <p><span class="label label-danger">￥<%=booklist.get(i).getPrice() %></span> <a href="servlet/ShoppingCart?action=add&userNumber=<%=userNumber  %>&id=<%=booklist.get(i).getId()  %>" class="btn btn-default" role="button">加入购物车</a></p>
      </div>
    </div>
  </div>
  	<% 
		}
	%>
</div>
 	<!-- 分页 -->
	<%
     	String before="";
     	String next="";
     	String nextclass="",beforeclass="";
		if(request.getAttribute("pagebefore")!=null){
			int num=(Integer)request.getAttribute("pagebefore");
			if(num!=-1){
				before="&pageNow="+num;
			}else{
				beforeclass="class=\"disabled\"";
			}
		}
		if(request.getAttribute("pagenext")!=null){
			int num=(Integer)request.getAttribute("pagenext");
			if(num!=-1){
				next="&pageNow="+num;
			}else{
				nextclass="class=\"disabled\"";
			}
		}
		int pageCount=(Integer)request.getAttribute("pageCount");//有多少页
    	int pageNowNew=1;
    	if(request.getAttribute("pageNowNew")!=null){
    		pageNowNew=(Integer)request.getAttribute("pageNowNew");//当前激活的页数
    	}
     %>
	 <nav class="col-md-offset-10">
	  <ul class="pagination">
	    <li <%=beforeclass%>>
	      <a <%=beforeclass%> href="servlet/Index?<%=before %>" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    <% 
	    	for(int i=0;i<pageCount;i++){
	    	String active=null;
	    	if((i+1)==pageNowNew){
	    		active="class=\"active\"";
	    	}else {active="";}
	    %>
	    <li <%=active%>><a href="servlet/Index?&pageNow=<%=i+1 %>"><%=i+1 %></a></li>
	    <% 
	    	}
	     %>
	    <li <%=nextclass%>>
	      <a <%=nextclass%> href="servlet/Index?<%=next %>" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	  </ul>
	</nav>

<br/><br/>
<h3><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true">读者推荐</h3>
      <!-- START THE FEATURETTES -->

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">三体(全三册) <span class="text-muted">内容简介：</span></h2>
          <p class="lead">文化大革命如火如荼进行的同时，军方探寻外星文明的绝秘计划“红岸工程”取得了突破性进展。但在按下发射键的那一刻，历经劫难的叶文洁没有意识到，她彻底改变了人类的命运。<br/>
地球文明向宇宙发出的第一声啼鸣，以太阳为中心，以光速向宇宙深处飞驰……<br/>
四光年外，“三体文明”正苦苦挣扎——三颗无规则运行的太阳主导下的百余次毁灭与重生逼迫他们逃离母星。而恰在此时，他们接收到了地球发来的信息。<br/>
在运用超技术锁死地球人的基础科学之后，三体人庞大的宇宙舰队开始向地球进发……人类的末日悄然来临。</p>
        </div>
        <div class="col-md-5">
          <a href="servlet/BookDetail?ISBN=23579654"><img class="featurette-image img-responsive center-block" src="image/santi.png" alt="Generic placeholder image"></a>
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7 col-md-push-5">
          <h2 class="featurette-heading">轻量级Java EE企业应用实战（第4版） <span class="text-muted">程序员必备：</span></h2>
          <p class="lead">本书是《轻量级Java EE企业应用实战》的第4版，第4版保持了前几版内容全面、深入的特点，主要完成全部知识的升级。<br>
　　本书介绍了Java EE领域的三个开源框架：Struts 2、Spring和Hibernate。其中Struts 2升级到2.3.16.3，Spring升级到4.0.4，Hibernate升级到4.3.5。本书还全面介绍了Servlet 3.1的新特性，以及Tomcat 8.0的配置和用法，本书的示例也应该在Tomcat 8.0上运行。<br>
　　本书重点介绍如何整合Struts 2.3+Spring 4.0+Hibernate 4.3进行Java EE开发，主要包括三部分。第一部分介绍了Java EE开发的基础知识，以及如何搭建开发环境。第二部分详细讲解了Struts 2.3、Spring 4.0和Hibernate 4.3三个框架的用法，介绍三个框架时，以Eclipse IDE的使用来上手，一步步带领读者深入三个框架的核心。<br>
这部分内容是笔者讲授“疯狂Java实训”的培训讲义，因此是本书的重点部分。这部分内容既包含了笔者多年开发经历的领悟，也融入了丰富的授课经验。第三部分示范开发了一个包含7个表，表之间具有复杂的关联映射、继承映射等关系，且业务也相对复杂的工作流案例，希望让读者理论联系实际，将三个框架真正运用到实际开发中去。<br>
该案例采用目前最流行、最规范的Java EE架构，整个应用分为领域对象层、DAO层、业务逻辑层、MVC层和视图层，各层之间分层清晰，层与层之间以松耦合的方法组织在一起。该案例既提供了IDE无关的、基于Ant管理的项目源码，也提供了基于Eclipse IDE的项目源码，最大限度地满足读者的需求</p>
        </div>
        <div class="col-md-5 col-md-pull-7">
          <a href="servlet/BookDetail?ISBN=9787121242533"><img class="featurette-image img-responsive center-block" src="image/javaEE.png" alt="Generic placeholder image"></a>
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">盗墓笔记 <span class="text-muted">经典小说：</span></h2>
          <p class="lead">极致悬念盛宴，不看《盗墓》枉读书！作为近年来最火爆的小说，《盗墓笔记》系列小说凭借着极具悬念的情节，诡异多变的气氛，细腻丰富的情感，个性迥异的人物，粗犷有力的文字而成为万众瞩目的焦点。<br/>
          	每次更新均超过千万的浏览，每卷出版均造成哄抢，每次签售均引发轰动。事实证明：近十年来，没有一部小说拥有过这样万众瞩目的关注。史上最具人气的畅销之作。本次出版的《盗墓笔记》六周年完美纪念套装更是将六年以来，<br/>
          	千万读者的无私之爱凝聚，让我们一起品尝这一场极致悬念的豪华盛宴！不读“盗墓”枉读书！</p>
        </div>
        <div class="col-md-5">
          <a href="servlet/BookDetail?ISBN=9787807407270"><img class="featurette-image img-responsive center-block" src="image/daomu.png" alt="Generic placeholder image"></a>
        </div>
      </div>

      <hr class="featurette-divider">

      <!-- /END THE FEATURETTES -->

<hr>
<footer>
  <p class="pull-right"><a href="#"><span class="glyphicon glyphicon-eject" aria-hidden="true"></a></p>
  <p>&copy; SCNU  2015</p>
</footer>
</div> <!-- /container -->
<script src="bootstrap-3.3.4-dist/js/bootstrap.js"></script>   
</body>
</html>
