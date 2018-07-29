<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>模拟商户</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='./css/Common.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='./css/Form.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='./css/Table.css'/>">
</head>
<body>
<script language="JavaScript" type="text/JavaScript">
    function doSubmit() {
        document.form1.submit();
    }
</script>
<p class="title">
    模拟商户
</p>
<form action="<c:url value="/Tx46701.do"/>" name="form1" method="POST">
    <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
            <td class="head" height="24" colspan="2">
                客户注册（46701）
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                银行机构信息(BkInst)
            </td>
            <td width="*" class="content">
                <input type="text" name="bkInst" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                日期（Date）
            </td>
            <td width="*" class="content">
                <input type="text" name="date" size="40" value="20170208"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                时间(Time)
            </td>
            <td width="*" class="content">
                <input type="text" name="time" size="40" value="142117"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                业务功能码(InstrCd)
            </td>
            <td width="*" class="content">
                <input type="text" name="instrCd" size="40" value="46701"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                最后分片标志(LstFrag)
            </td>
            <td width="*" class="content">
                <input type="text" name="lstFrag" size="40" value="N"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                流水号(Ref)
            </td>
            <td width="*" class="content">
                <input type="text" name="ref" size="40" value="20170208142117313313"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                机构标识(InstId)
            </td>
            <td width="*" class="content">
                <input type="text" name="instId" size="40" value="P0008000"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                机构类型(InstType)
            </td>
            <td width="*" class="content">
                <input type="text" name="instType" size="40" value="0"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                应用系统类型(SysType)
            </td>
            <td width="*" class="content">
                <input type="text" name="sysType" size="40"
                       value="4"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                交易发起方(TradSrc)
            </td>
            <td width="*" class="content">
                <input type="text" name="tradSrc" size="40"
                       value="0"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                版本号(Ver)
            </td>
            <td width="*" class="content">
                <input type="text" name="ver" size="40"
                       value="V3.0.0.27"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                业务类别(BusType)
            </td>
            <td width="*" class="content">
                <input type="text" name="busType" size="40"
                       value="0"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                币种(Ccy)
            </td>
            <td width="*" class="content">
                <input type="text" name="ccy" size="40"
                       value="0"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                证件号码(CertId)
            </td>
            <td width="*" class="content">
                <input type="text" name="certId" size="40"
                       value="420684198612066412"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                证件类型(CertType)
            </td>
            <td width="*" class="content">
                <input type="text" name="certType" size="40"
                       value="1"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                客户姓名(Name)
            </td>
            <td width="*" class="content">
                <input type="text" name="name" size="40"
                       value="阿凡达"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                客户类型(Type)
            </td>
            <td width="*" class="content">
                <input type="text" name="type" size="40"
                       value="0"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                账号(Id)
            </td>
            <td width="*" class="content">
                <input type="text" name="id" size="40"
                       value="20170208003"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">
                操作类型(OPFlag)
            </td>
            <td width="*" class="content">
                <input type="text" name="oPFlag" size="40"
                       value="0"/>
            </td>
        </tr>
        <tr>
            <td width="200" height="28"/>
            <td width="*">
                <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'"
                       onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()">
            </td>
        </tr>
    </table>
    <input type="hidden" name="txCode" value="46701"/>
    <input type="hidden" name="txName" value="客户注册"/>
</form>
</body>
</html>
