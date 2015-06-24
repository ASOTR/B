$(function () {
$("#submit_btn").click(function () {
	var flag=true;
    if ($.trim($("#realName").val()) == "") {//验证姓名
    	$("#realNameTips").html("姓名不能为空!");
		$("#realNameTips").css("color","red");
    	$("#realName").css("border","1px solid red");
       	flag=false;
    }else{$("#realNameTips").html("");$("#realName").css("border","");}
    if($.trim($("#email").val())!=""&&!$.trim($("#email").val()).match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
    	 $("#emailTips").html("邮箱格式不正确!");
		 $("#emailTips").css("color","red");
         $("#email").css("border","1px solid red");
        flag=false;
    }else{$("#emailTips").html("");$("#email").css("border","");}
	if($.trim($("#addr").val()) == "") {//验证地址
		 $("#addrTips").html("地址不能为空!");
		 $("#addrTips").css("color","red");
    }else{$("#addrTips").html("");$("#addr").css("border","");}
	if($.trim($("#school").val()) == "") {//验证学校
	    $("#schoolTips").html("学校不能为空!");
	    $("#schoolTips").css("color","red");
        $("#school").css("border","1px solid red");
    }else{$("#school").html("");$("#school").css("border","");} 
    if($.trim($("#phone").val()) == "") {//验证电话
        $("#phoneTips").html("电话不能为空!");
        $("#phoneTips").css("color","red");
        $("#phone").css("border","1px solid red");
    }else if(isNaN($.trim($("#phone").val()))){
        $("#phoneTips").html("电话格式不正确!");
        $("#phoneTips").css("color","red");
        $("#phone").css("border","1px solid red");
        flag=false;
    }else{$("#phoneTips").html("");$("#phone").css("border","");}          
    if(flag==true){//提交表单
        $("#submit_btn").attr("disabled",true);
        alert("保存成功!");
        document.userForm.submit();
    }else {
    	return false;
    }
});
$("#realName").blur(function () {
   if ($.trim($("#realName").val()) == "") {//验证姓名
	   $("#realNameTips").html("姓名不能为空!");
		$("#realNameTips").css("color","red");
    	$("#realName").css("border","1px solid red");
       	flag=false;
    }else{$("#realNameTips").html("");$("#realName").css("border","");}
});
$("#email").blur(function () {
    if($.trim($("#email").val())!=""&&!$.trim($("#email").val()).match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
   	 $("#emailTips").html("邮箱格式不正确!");
        $("#emailTips").css("color","red");
        $("#email").css("border","1px solid red");
       flag=false;
   }else{$("#emailTips").html("");$("#email").css("border","");}
});
$("#addr").blur(function () {
   if($.trim($("#addr").val()) == "") {//验证地址
        $("#addrTips").html("地址不能为空!");
         $("#addrTips").css("color","red");
        $("#addr").css("border","1px solid red");
        flag=false;
    }else{$("#addrTips").html("");$("#addr").css("border","");}
});
$("#school").blur(function () {
  if($.trim($("#school").val()) == "") {//验证学校
        $("#schoolTips").html("学校不能为空!");
        $("#schoolTips").css("color","red");
        $("#school").css("border","1px solid red");
        flag=false;
    }else{$("#schoolTips").html("");$("#school").css("border","");} 
});
$("#phone").blur(function () {
   if($.trim($("#phone").val()) == "") {//验证电话
        $("#phoneTips").html("电话不能为空!");
        $("#phoneTips").css("color","red");
        $("#phone").css("border","1px solid red");
        flag=false;
    }else if(isNaN($.trim($("#phone").val()))){
        $("#phoneTips").html("电话格式不正确!");
        $("#phoneTips").css("color","red");
        $("#phone").css("border","1px solid red");
        flag=false;
    }else{$("#phoneTips").html("");$("#phone").css("border","");}
    });        
});
