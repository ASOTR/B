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
      		<li class="active"><a href="servlet/BookServlet?action=show">图书管理</a></li>
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
<ul class="nav nav-pills nav-stacked col-md-2">
     <li class="active"><a href="servlet/UserServlet?action=show&authority=0">图书列表</a></li>
 	<li>
		<!-- href触发模态弹出窗元素 -->
		<a data-toggle="modal" href="#mymodal">添加图书</a>
		<!-- 模态弹出窗内容 -->
		<div id="mymodal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="mymodal-link">
    		<div class="modal-dialog">
    			<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">添加图书</h4>
					</div>
				<form action="servlet/BookServlet?action=add" method="post">
				<div class="modal-body col-md-8">
				<p>填写要添加的图书信息</p>
				书名：<input name="bookName" type="text" class="form-control" placeholder="书名">
				出版社：<input name="publishing_Company" type="text" class="form-control" placeholder="出版社">
				出版日期：<input name="date" type="text" class="form-control" placeholder="出版日期">
				ISBN：<input name="ISBN" type="text" class="form-control" placeholder="ISBN">
				图书描述：<input name="book_Description" type="text" class="form-control" placeholder="图书描述">
				图书封面：<input name="imgUrl" type="text" class="form-control" placeholder="图书封面">
				单价：<input name="price" type="text" class="form-control" placeholder="单价">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-primary">添加</button>
				</div>
				</form>
				</div>
			</div>
		</div>
 	</li>
 </ul>
 
 <div class="table-responsive  col-md-9">
 <table class="table table-striped table-bordered table-hover">
 <caption>图书管理</caption>
  	<tr>
  		<th><input type="checkbox">全选</th>
		<th>图书ID</th>
		<th>书名</th>
		<th>出版社</th>
		<th>出版日期</th>
		<th>ISBN</th>
		<th>图书描述</th>
		<th>图书封面</th>
		<th>单价</th>
		<th colspan="2" class="text-center">操作</th>
	</tr>
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
	<tr>
		<td><input type="checkbox"></td>
		<td><%=booklist.get(i).getId() %></td>
		<td><%=booklist.get(i).getBookName() %></td>
		<td><%=booklist.get(i).getPublishing_Company() %></td>
		<td><%=booklist.get(i).getDate() %></td>
		<td><%=booklist.get(i).getISBN() %></td>
		<td><%=booklist.get(i).getBook_Description() %></td>
		<td><%=booklist.get(i).getImgUrl() %></td>
		<td><%=booklist.get(i).getPrice() %></td>
		<td>
			<!-- href触发模态弹出窗  修改用户信息 -->
			<a data-toggle="modal" href="#mychangemodal" onclick="setid('<%=booklist.get(i).getId()%>',
			'<%=booklist.get(i).getBookName()%>','<%=booklist.get(i).getPublishing_Company()%>',
			'<%=booklist.get(i).getDate()%>','<%=booklist.get(i).getISBN()%>',
			'<%=booklist.get(i).getBook_Description()%>','<%=booklist.get(i).getImgUrl()%>',
			'<%=booklist.get(i).getPrice()%>')">修改</a>
		</td>
		<td>
			<a href="servlet/BookServlet?action=delete&id=<%=booklist.get(i).getId()%>" onclick="delcfm()">删除</a>
		</td>
	</tr>
	<% 
		}
	%>
 </table>
	<!-- 模态弹出窗 修改用户信息 -->
	<div id="mychangemodal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="mymodal-link">
  		<div class="modal-dialog">
  			<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">修改用户</h4>
			</div>
			<form name="changeUserForm" action="" method="post">
				<div class="modal-body col-md-8">
				<p>填写要修改的用户信息</p>
				书名：<input id="bookName" name="bookName" type="text" class="form-control" placeholder="书名">
				出版社：<input id="publishing_Company" name="publishing_Company" type="text" class="form-control" placeholder="出版社">
				出版日期：<input id="date" name="date" type="text" class="form-control" placeholder="出版日期">
				ISBN：<input id="ISBN" name="ISBN" type="text" class="form-control" placeholder="ISBN">
				图书描述：<input id="book_Description" name="book_Description" type="text" class="form-control" placeholder="图书描述">
				图书封面：<input id="imgUrl" name="imgUrl" type="text" class="form-control" placeholder="图书封面">
				单价：<input id="price" name="price" type="text" class="form-control" placeholder="单价">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-primary">修改</button>
				</div>
			</form>
			</div>
		</div>
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
	 <nav>
	  <ul class="pagination">
	    <li <%=beforeclass%>>
	      <a <%=beforeclass%> href="servlet/BookServlet?action=show<%=before %>" aria-label="Previous">
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
	    <li <%=active%>><a href="servlet/BookServlet?action=show&pageNow=<%=i+1 %>"><%=i+1 %></a></li>
	    <% 
	    	}
	     %>
	    <li <%=nextclass%>>
	      <a <%=nextclass%> href="servlet/BookServlet?action=show<%=next %>" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	  </ul>
	</nav>
	<script type="text/javascript">
	var changeid;
	function delcfm(){
		if(!confirm("确定要删除？")){
			window.event.returnValue=false;
		}
	}
	function setid(id,bookName,publishing_Company,date,ISBN,book_Description,imgUrl,price){//修改模态窗口的id号
		changeid=id;
		document.changeUserForm.action = "servlet/BookServlet?action=change&changeid="+changeid;
		$("#bookName").attr("value",bookName);
		$("#publishing_Company").attr("value",publishing_Company);
		$("#date").attr("value",date);
		$("#ISBN").attr("value",ISBN);
		$("#book_Description").attr("value",book_Description);
		$("#imgUrl").attr("value",imgUrl);
		$("#price").attr("value",price);
	}
	</script>
	<script src="bootstrap-3.3.4-dist/js/bootstrap.js"></script>
</body>

</html>
