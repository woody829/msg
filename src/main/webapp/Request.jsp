<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script language="JavaScript" type="text/javascript" src="./scripts/jquery/jquery-3.2.0.min.js"></script>
<script language="JavaScript" type="text/javascript">
    function sendMsg(flag) {
        $("[name=encryptFlag]").val(flag);
        $.ajax({
            type: 'post',
            url: '<c:url value="/SendMessage.do"/>',
            cache: false,
            data: {
                message: $("[name=message]").val(),
                encryptFlag: $("[name=encryptFlag]").val()
            },
            dataType: 'text',
            success: function (data) {
                alert('报文发送成功');
                alert(data);
                $("[name=prettyText]").val(data);
                $("[name=msgTD]").html('响应报文');
                $("[name=button]").hide();
            },
            error: function () {
                alert('ajax调用失败');
            }
        });
    }
</script>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>模拟商户</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='./css/Common.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='./css/Form.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='./css/Table.css'/>">

</head>
<body>
<p class="title">模拟商户</p>
<form name="form1" method="POST">
    <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
            <td class="head" height="24" colspan="2">${msgDTO.txName}(${msgDTO.txCode})</td>
        </tr>
        <tr class="mouseout">
            <td name="msgTD" width="100" class="label" height="400">请求报文</td>
            <td width="*" class="content">
            <textarea name="prettyText" cols="120" rows="20" wrap="off">
                <c:out value="${msgDTO.prettyText}"/></textarea>
            </td>
        </tr>
    </table>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
            <td height="10" colspan="2"/>
        </tr>
        <tr>
            <td width="200" height="28"/>
            <td width="*">
                <input type="button" name="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'"
                       onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="加密提交"
                       onclick="sendMsg('Y')">
            </td>
            <td width="*">
                <input type="button" name="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'"
                       onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="非加密提交"
                       onclick="sendMsg('N')">
            </td>
        </tr>
    </table>

    <input type="hidden" name="message" value="<c:out value="${msgDTO.originText}"/>"/>
    <input type="hidden" name="encryptFlag"/>
    <input type="hidden" name="txCode" value="${txCode}"/>
    <input type="hidden" name="txName" value="${txName}"/>
</form>

</body>
</html>