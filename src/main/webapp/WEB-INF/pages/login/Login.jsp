<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    response.setHeader("Pragma","No-cache");   
    response.setHeader("Cache-Control","no-cache");   
    response.setDateHeader("Expires", 0); 
    String root = request.getContextPath();
    String action = root + "/Login.do";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>模拟商户系统</title>
<style type="text/css">
<!--
body {
	background-color: #3083c2;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
body,td,th {
	color: #FFFFFF;
}
.STYLE1 {
	font-family: "宋体";
	font-size: 14px;
}
-->
</style>
<script type="text/javascript">
    /*
    window.onload = function() {
        document.loginFrm.userName.focus();
        document.onkeydown=function () {
         if   (event.keyCode==13)   
         {
          window.event.returnValue = false;
          if(document.activeElement.name == 'userName') {
            document.loginFrm.password.focus();
            
          }
          else if(document.activeElement.name == 'password') {
            document.loginFrm.submit();
            
          }
          
         }
        }
    }
    //屏蔽右键
    document.oncontextmenu=function(){return false};
    
    function keydown() {
        if ((window.event.altKey)&& 
            ((window.event.keyCode==37)||   //屏蔽 Alt+ 方向键 ← 
            (window.event.keyCode==39))){   //屏蔽 Alt+ 方向键 → 
           event.returnValue=false; 
        }
        if (
            (event.keyCode==116)||                 //屏蔽 F5 刷新键 
            (event.ctrlKey && event.keyCode==82)){ //Ctrl + R 
           event.keyCode=0;
           event.returnValue=false; 
        } 
        if (event.keyCode==122){event.keyCode=0;event.returnValue=false;}  //屏蔽F11 
        if (event.ctrlKey && event.keyCode==78) event.returnValue=false;   //屏蔽 Ctrl+n 
        if (event.shiftKey && event.keyCode==121)event.returnValue=false;  //屏蔽 shift+F10 
        if (window.event.srcElement.tagName == "A" && window.event.shiftKey)  
            window.event.returnValue = false;             //屏蔽 shift 加鼠标左键新开一网页 
    }
    */
    function login() {
      document.loginFrm.submit();
    }
    
    function reset() {
      document.loginFrm.reset();
    }
</script>
</head>

<body onkeydown="keydown();">
<div style="position:absolute;left:expression((body.clientWidth-1680)/2);top:expression((body.clientHeight-532)/2);width:1680;height:532">

<br><br>
<P style="font-size: 45pt;font-weight:bold; "  align="center">模&nbsp;拟&nbsp;商&nbsp;户&nbsp;系&nbsp;统</P>
<table width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0">
  <tr>
    <td>
    <form action="<%=action%>" name="loginFrm" method="post">
        <table align="center" border="0" cellpadding="0" cellspacing="0" class="type">
          <tr>
            <td height="30" align="right"> <span class="STYLE1">用户名</span>：</td>
            <td><input type="text" name="userName" style="width: 150px"/></td>
          </tr>
          <tr>
            <td height="10"></td>
            <td></td>
          </tr>
          <tr>
            <td height="30" align="right"><span class="STYLE1">密&nbsp;&nbsp;码</span>：</td>
            <td><input type="password" name="password" style="width: 150px"/></td>
          </tr>
          <tr>
            <td height="10"></td>
            <td valign="top"></td>
          </tr>
          <tr>
            <td height="1"></td>
            <td valign="top"><img src="<%=root%>/images/login_2.png" onclick="login()"/>
              <a style="width: 30"></a><img src="<%=root%>/images/login_4.png" onclick="reset()"/>
              </td>
          </tr>
        </table>
        </form>
    </td>
  </tr>
</table>
</div>
</body>
<% String info = (String)request.getAttribute("info"); 
    if(info != null){%>
 <script type="text/javascript">
  alert("<%=info%>");
 </script> 
    
<% 
   request.removeAttribute("info");
   }
%>
</html>
