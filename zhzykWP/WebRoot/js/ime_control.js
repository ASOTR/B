
function getEvent() //ͬʱ����ie��ff��д��
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
} //�����:

var ime_active = 'no';  //�ж����뷨 

//���̷���  
// num_lock    С���� 
// large_num   ��������� ��Ĭ�ϣ�
// right_hand  ���������
// ͬʱ����  onkeydown �� onkeyup �¼�  textfield1 Ϊ�����id
//   onkeydown = "keydownHandler(event, 'textfield1')"  onkeyup = "keyupHandler(event, 'textfield1')"
//
var zhzyk_keyboard = 'large_num'; 



// �¼� ��  textareaId
function keydownHandler(obj) { 
	 
	var zhzyk_imeKey = (navigator.appName.indexOf("Opera")!=-1) ?  197: 229 ; 
	// Internet Explorer/Chrome �����ʹ�� event.keyCode ȡ�ر����µ��ַ����� Netscape/Firefox/Opera �������ʹ�� event.which��
	
	var theEvent = getEvent();
	alert();
	var keynum = theEvent.keyCode;
     
	if(!window.event && keynum == 0){  // ��� 
		ime_active ='yes';
		obj.disabled="disabled";
		setTimeout("initTextarea("+obj+")", 1);  
		alert('��ʹ���ݺ����뷨');
	}	
	 //�ǻ��
	
	if (keynum == zhzyk_imeKey) { 
	    ime_active ='yes';
	   	
		}else { 
			ime_active = 'no';
	} 
	 
} 

function initTextarea(obj){ 
	obj.disabled="";
}
// �¼� ��  textareaId
function keyupHandler(e, obj) { 
	// Internet Explorer/Chrome �����ʹ�� event.keyCode ȡ�ر����µ��ַ����� Netscape/Firefox/Opera �������ʹ�� event.which��
	var theEvent = window.event || e;
	var keynum = theEvent.keyCode ? theEvent.keyCode : theEvent.which;	
	var zhzyk_imeKey = (navigator.appName.indexOf("Opera")!=-1) ?  197: 229 ; 
	if (ime_active == 'yes') {  //ʹ���ⲿ���뷨 
             
			if((keynum > 64 && keynum < 91) || keynum ==  zhzyk_imeKey ){
				if(!(zhzyk_keyboard == 'large_num' && (keynum == 84 || keynum == 89))){ //��������ַ���Ҫ�õ�y, t
					obj.disabled="disabled";
				    setTimeout("initTextarea("+obj+")", 5);
					alert('��ʹ���ݺ����뷨');
				} 
				
			}
	} 
		

    
}  