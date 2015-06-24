
function getEvent() //同时兼容ie和ff的写法
{ 
    if(document.all)  return window.event;   
        func=getEvent.caller;       
    while(func!=null){ 
       var arg0=func.arguments[0];
	   if(arg0)
	   {
	      if((arg0.constructor==Event || arg0.constructor ==MouseEvent) || (typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation))
	      { 
	         return arg0;
	      }
	   }
   		func=func.caller;
   }
        return null;
} //如调用:

var ime_active = 'no';  //判断输入法 

//键盘方案  
// num_lock    小键盘 
// large_num   大键盘数字 （默认）
// right_hand  大键盘右手
// 同时监听  onkeydown 和 onkeyup 事件  textfield1 为输入框id
//   onkeydown = "keydownHandler(event, 'textfield1')"  onkeyup = "keyupHandler(event, 'textfield1')"
//
var zhzyk_keyboard = 'large_num'; 



// 事件 和  textareaId
function keydownHandler(obj) { 
	 
	var zhzyk_imeKey = (navigator.appName.indexOf("Opera")!=-1) ?  197: 229 ; 
	// Internet Explorer/Chrome 浏览器使用 event.keyCode 取回被按下的字符，而 Netscape/Firefox/Opera 等浏览器使用 event.which。
	
	var theEvent = getEvent();
	alert();
	var keynum = theEvent.keyCode;
     
	if(!window.event && keynum == 0){  // 火狐 
		ime_active ='yes';
		obj.disabled="disabled";
		setTimeout("initTextarea("+obj+")", 1);  
		alert('请使用纵横输入法');
	}	
	 //非火狐
	
	if (keynum == zhzyk_imeKey) { 
	    ime_active ='yes';
	   	
		}else { 
			ime_active = 'no';
	} 
	 
} 

function initTextarea(obj){ 
	obj.disabled="";
}
// 事件 和  textareaId
function keyupHandler(e, obj) { 
	// Internet Explorer/Chrome 浏览器使用 event.keyCode 取回被按下的字符，而 Netscape/Firefox/Opera 等浏览器使用 event.which。
	var theEvent = window.event || e;
	var keynum = theEvent.keyCode ? theEvent.keyCode : theEvent.which;	
	var zhzyk_imeKey = (navigator.appName.indexOf("Opera")!=-1) ?  197: 229 ; 
	if (ime_active == 'yes') {  //使用外部输入法 
             
			if((keynum > 64 && keynum < 91) || keynum ==  zhzyk_imeKey ){
				if(!(zhzyk_keyboard == 'large_num' && (keynum == 84 || keynum == 89))){ //大键盘数字方案要用到y, t
					obj.disabled="disabled";
				    setTimeout("initTextarea("+obj+")", 5);
					alert('请使用纵横输入法');
				} 
				
			}
	} 
		

    
}  