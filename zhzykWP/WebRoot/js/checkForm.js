$(function () {
$("#submit_btn").click(function () {
	var flag=true;
    if ($.trim($("#realName").val()) == "") {//��֤����
    	$("#realNameTips").html("��������Ϊ��!");
		$("#realNameTips").css("color","red");
    	$("#realName").css("border","1px solid red");
       	flag=false;
    }else{$("#realNameTips").html("");$("#realName").css("border","");}
    if($.trim($("#email").val())!=""&&!$.trim($("#email").val()).match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
    	 $("#emailTips").html("�����ʽ����ȷ!");
		 $("#emailTips").css("color","red");
         $("#email").css("border","1px solid red");
        flag=false;
    }else{$("#emailTips").html("");$("#email").css("border","");}
	if($.trim($("#addr").val()) == "") {//��֤��ַ
		 $("#addrTips").html("��ַ����Ϊ��!");
		 $("#addrTips").css("color","red");
    }else{$("#addrTips").html("");$("#addr").css("border","");}
	if($.trim($("#school").val()) == "") {//��֤ѧУ
	    $("#schoolTips").html("ѧУ����Ϊ��!");
	    $("#schoolTips").css("color","red");
        $("#school").css("border","1px solid red");
    }else{$("#school").html("");$("#school").css("border","");} 
    if($.trim($("#phone").val()) == "") {//��֤�绰
        $("#phoneTips").html("�绰����Ϊ��!");
        $("#phoneTips").css("color","red");
        $("#phone").css("border","1px solid red");
    }else if(isNaN($.trim($("#phone").val()))){
        $("#phoneTips").html("�绰��ʽ����ȷ!");
        $("#phoneTips").css("color","red");
        $("#phone").css("border","1px solid red");
        flag=false;
    }else{$("#phoneTips").html("");$("#phone").css("border","");}          
    if(flag==true){//�ύ��
        $("#submit_btn").attr("disabled",true);
        alert("����ɹ�!");
        document.userForm.submit();
    }else {
    	return false;
    }
});
$("#realName").blur(function () {
   if ($.trim($("#realName").val()) == "") {//��֤����
	   $("#realNameTips").html("��������Ϊ��!");
		$("#realNameTips").css("color","red");
    	$("#realName").css("border","1px solid red");
       	flag=false;
    }else{$("#realNameTips").html("");$("#realName").css("border","");}
});
$("#email").blur(function () {
    if($.trim($("#email").val())!=""&&!$.trim($("#email").val()).match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
   	 $("#emailTips").html("�����ʽ����ȷ!");
        $("#emailTips").css("color","red");
        $("#email").css("border","1px solid red");
       flag=false;
   }else{$("#emailTips").html("");$("#email").css("border","");}
});
$("#addr").blur(function () {
   if($.trim($("#addr").val()) == "") {//��֤��ַ
        $("#addrTips").html("��ַ����Ϊ��!");
         $("#addrTips").css("color","red");
        $("#addr").css("border","1px solid red");
        flag=false;
    }else{$("#addrTips").html("");$("#addr").css("border","");}
});
$("#school").blur(function () {
  if($.trim($("#school").val()) == "") {//��֤ѧУ
        $("#schoolTips").html("ѧУ����Ϊ��!");
        $("#schoolTips").css("color","red");
        $("#school").css("border","1px solid red");
        flag=false;
    }else{$("#schoolTips").html("");$("#school").css("border","");} 
});
$("#phone").blur(function () {
   if($.trim($("#phone").val()) == "") {//��֤�绰
        $("#phoneTips").html("�绰����Ϊ��!");
        $("#phoneTips").css("color","red");
        $("#phone").css("border","1px solid red");
        flag=false;
    }else if(isNaN($.trim($("#phone").val()))){
        $("#phoneTips").html("�绰��ʽ����ȷ!");
        $("#phoneTips").css("color","red");
        $("#phone").css("border","1px solid red");
        flag=false;
    }else{$("#phoneTips").html("");$("#phone").css("border","");}
    });        
});
