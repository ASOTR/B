<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>纵横信息数字化学习在线写作及反馈系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="纵横数字化,在线写作,纵横秘书处">
	<meta http-equiv="description" content="纵横信息数字化学习在线写作系统">
	<link rel="stylesheet" type="text/css" href="css/Login.css">
	<link rel="stylesheet" href="bootstrap-3.3.4-dist/css/bootstrap.min.css">
	<style type="text/css">
		body{
			background-color: #a5cbfa;
		}
	</style>
  </head>
  <body>
  	<div class="main">
  		<div class="right_login">
  			<!-- 登录表单 -->
			<form>
			  <div class="form-group">
			    <label for="nameName">用户名：</label><br/>
			    <input type="text" name="userName" class="form-control" id="nameName" placeholder="输入账号"/>
			  </div>
			  <div class="form-group">
			    <label for="password">登录密码：</label><br/>
			    <input type="password" name="password" class="form-control" id="password" placeholder="输入密码"/>
			  </div><br/>
			  <select class="form-control">
			  	<option value="0">学生</option>
			  	<option value="1">教师</option>
			  	<option value="2">评阅</option>
				<option value="3">管理</option>
			  </select>
			  <br/>
			  <input type="submit" class="btn btn-warning" value="登&nbsp;&nbsp;&nbsp;录"/><br/><br/>
			  <p><a href="##"><font color="#FF6231" size="2">学生个人版（免登陆）</font></a></p>
			</form>  		
  		</div>
  	</div>
  </body>
</html>
