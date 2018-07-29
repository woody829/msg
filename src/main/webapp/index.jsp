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
              <a style="width:30"></a><img src="<%=root%>/images/login_4.png" onclick="reset()"/>
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
