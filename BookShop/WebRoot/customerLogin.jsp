<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>网上购书</title>
    <!-- Bootstrap -->
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.4-dist/css/bootstrap.min.css">
    <link href="css/login.css" rel="stylesheet">
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
    <script src="js/jquery.js" type="text/javascript"></script>
  </head>
  
  <body>
      <div class="container">
      <form id="Form1" class="form-signin" method="post">
        <h2 class="form-signin-heading">网上购书会员登录</h2>
        <label for="user" class="sr-only">账号</label>
        <input type="text" id="user" name="user" class="form-control" placeholder="请输入账号" required autofocus>
        <label for="password" class="sr-only">密码</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="请输入密码" required>
        <span id="tips" class="help-block"></span>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住账号和密码（一周之内）
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登 录</button>
      </form>
     </div> <!-- /container -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    
   	<script type="text/javascript">
 	 $(function(){  
	    $('#Form1').bind('submit', function(){
	    var userNumber=$("input[name='user']").val();
		var password=$("input[name='password']").val();
			$.ajax({
				url:"servlet/SysLogin?action=customer",
				data:{user:userNumber,password:password},
				type:"post",
				success:function(data){
					if(data==-1){//账号不存在
						document.getElementById("tips").innerHTML="登陆失败，账号不存在！";
					}else if(data=="0"){//密码错误
					   document.getElementById("tips").innerHTML="登陆失败，密码错误！";
					}else if(data=="1"){//登陆成功
					   window.location.href="index.jsp";
					}
				}
			});
		return false; // 返回值为false，将阻止表单提交
	    });
	}); 
   	</script>
  </body>
</html>
