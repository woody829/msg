<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模拟商户</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Common.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Form.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Table.css">
</head>
<body>
<p class="title">模拟商户</p>
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">商户订单支付（1100）</td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">商户订单支付（直通车）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1111.jsp">1111</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">商户订单支付（不要确认）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1112.jsp">1112</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">商户订单支付交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1120.jsp">1120</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">付款账户信息查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1121.jsp">1121</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">商户订单退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1131.jsp">1131</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">商户订单退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1132.jsp">1132</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">商户订单原路退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1133.jsp">1133</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">市场订单支付（1300）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单支付（直通车）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1311.jsp">1311</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单支付（不要确认）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1312.jsp">1312</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1320.jsp">1320</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">付款账户信息查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1321.jsp">1321</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1330.jsp">1330</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单原路退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1333.jsp">1333</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单结算（结算）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1341.jsp">1341</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单结算（退款）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1343.jsp">1343</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单结算交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1350.jsp">1350</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订购支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1351.jsp">1351</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订购支付交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1352.jsp">1352</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订购支付退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1354.jsp">1354</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订购支付退款交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1355.jsp">1355</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单单笔代收</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1361.jsp">1361</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单单笔代收交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1362.jsp">1362</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单批量代收</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1365.jsp">1365</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单批量代收交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1366.jsp">1366</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单绑定支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1371.jsp">1371</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单绑定支付交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1372.jsp">1372</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单绑定支付退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1373.jsp">1373</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单绑定支付退款交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1374.jsp">1374</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单绑定支付（发送短信）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1375.jsp">1375</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单绑定支付（短信验证并支付）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1376.jsp">1376</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单POS支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1380.jsp">1380</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单POS支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1382.jsp">1382</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单资金流向查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1390.jsp">1390</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单认证支付发送验证短信</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1391.jsp">1391</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单认证支付（验证短信并支付，无页面）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1392.jsp">1392</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单认证支付（开通并支付，到银联页面）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1393.jsp">1393</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单认证支付交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1395.jsp">1395</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单认证支付退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1396.jsp">1396</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单认证支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1397.jsp">1397</a></td>
    </tr>
    
    <tr>
        <td class="head" height="24" colspan="2">保证金支付（1400）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">保证金退还</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1431.jsp">1431</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">保证金退还查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1432.jsp">1432</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">批量代付（1500）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1510.jsp">1510</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1520.jsp">1520</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代付交易对账</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1550.jsp">1550</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">批量代扣（1600）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代扣</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1610.jsp">1610</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代扣查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1620.jsp">1620</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代扣交易对账</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1650.jsp">1650</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">预授权（1700）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">预授权订单支付（直通车）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1711.jsp">1711</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24"> 预授权订单支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1713.jsp">1713</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">预授权撤销</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1721.jsp">1721</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24"> 预授权撤销查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1723.jsp">1723</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">预授权扣款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1731.jsp">1731</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24"> 预授权扣款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1733.jsp">1733</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">预授权结算</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1741.jsp">1741</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">对账单（1800）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">下载交易对账单</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1810.jsp">1810</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">下载监管银行对账单</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1840.jsp">1840</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">一站式代收付（1900）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">一站式代收付交易</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1910.jsp">1910</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">一站式代收付批次查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1920.jsp">1920</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">一站式代收付明细查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1930.jsp">1930</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">一站式代收付对账</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1950.jsp">1950</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">单笔代收（2000）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">单笔代收请求</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2011.jsp">2011</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">单笔代收交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2020.jsp">2020</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">分步支付（2100）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">分步支付请求</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2110.jsp">2110</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">分步支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2120.jsp">2120</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">分步支付退款请求</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2131.jsp">2131</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">分步支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2132.jsp">2132</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">O2O支付（2200）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O订单支付预生成</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2210.jsp">2210</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O订单支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2220.jsp">2220</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O订单支付退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2231.jsp">2231</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O订单支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2232.jsp">2232</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">账户验证(2300)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户验证</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2310.jsp">2310</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">身份验证</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2320.jsp">2320</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">实名认证</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2391.jsp">2391</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">实名认证交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2392.jsp">2392</a></td>
    </tr>
    
    <!--  -->
    <tr>
        <td class="head" height="24" colspan="2">快捷支付</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">建立绑定关系</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2501.jsp">2501</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">绑定关系查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2502.jsp">2502</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">解除绑定关系</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2503.jsp">2503</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2511.jsp">2511</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2512.jsp">2512</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付退款（轧差方式）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2521.jsp">2521</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2522.jsp">2522</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">建立绑定关系（发送验证短信）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2531.jsp">2531</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">建立绑定关系（验证并绑定）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2532.jsp">2532</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付（发送验证短信）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2541.jsp">2541</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付（验证并支付）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2542.jsp">2542</a></td>
    </tr>
    <!-- demo -->
    <tr class="mouseout">
        <td width="200" class="content" height="24">建立绑定关系demo（发送短信+验证+绑定）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2531_2532.jsp">2531+2532</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">开通认证支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2551.jsp">2551</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">开通认证支付结果查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2553.jsp">2553</a></td>
    </tr>
    <!--<tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付demo（发送短信+验证+支付）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2541_2542.jsp">2541+2542</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">绑定并支付demo（绑定+发送短信+验证+支付）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2511_2541_2542.jsp">2511+2541+2542</a></td>
    </tr>
    
    -->
    <!-- P2P Start -->
    <tr>
        <td class="head" height="24" colspan="2">P2P项目(3000)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3111.jsp">3111</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目支付交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3116.jsp">3116</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目结算</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3131.jsp">3131</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目结算查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3136.jsp">3136</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目账户转账结算</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3137.jsp">3137</a></td>
    </tr>       
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目账户转账结算查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3138.jsp">3138</a></td>
    </tr>        
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3141.jsp">3141</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3146.jsp">3146</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">自动投资（基本户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3151.jsp">3151</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">自动投资查询（基本户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3156.jsp">3156</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目批量结算（基本户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3161.jsp">3161</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目批量结算查询（基本户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3162.jsp">3162</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目支付（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3211.jsp">3211</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目支付查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3216.jsp">3216</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目结算（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3231.jsp">3231</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目结算查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3236.jsp">3236</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目账户转账结算（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3237.jsp">3237</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目账户转账结算查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3238.jsp">3238</a></td>
    </tr>        
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3241.jsp">3241</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3246.jsp">3246</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">自动投资（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3251.jsp">3251</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">自动投资查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3256.jsp">3256</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目批量结算（专属账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3261.jsp">3261</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目批量结算查询（专属账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3262.jsp">3262</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目到帐（华夏定制）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3291.jsp">3291</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目到帐查询（华夏定制）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3292.jsp">3292</a></td>
    </tr>
    
    <tr class="mouseout">
        <td width="200" class="content" height="24">优惠券批量下发</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3310.jsp">3310</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">优惠券批量查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3312.jsp">3312</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">优惠券过期回收</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3315.jsp">3315</a></td>
    </tr>
    <!-- P2P End -->
    
    <!-- P2POptimize Start -->
    <tr>
        <td class="head" height="24" colspan="2">P2P支付-基本户(3600)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目信息发布</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3601.jsp">3601</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目信息查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3602.jsp">3602</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3611.jsp">3611</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3612.jsp">3612</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目结算</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3631.jsp">3631</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目结算查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3632.jsp">3632</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款（从融资人银行账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3641.jsp">3641</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3642.jsp">3642</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款（从担保公司或平台的支付账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3643.jsp">3643</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3644.jsp">3644</a></td>
    </tr>
     <!-- P2POptimize End -->
    
     <!-- PaymentAccount Start -->
    <tr>
        <td class="head" height="24" colspan="2">支付账户(4000)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">统一账户注册</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4201.jsp">4201</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">统一账户注册结果查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4202.jsp">4202</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户余额查询签约</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4210.jsp">4210</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户余额查询解约</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4211.jsp">4211</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户余额查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4212.jsp">4212</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户扣款签约</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4220.jsp">4220</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户扣款解约</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4221.jsp">4221</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户注册（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4231.jsp">4231</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户注册查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4232.jsp">4232</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户登录（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4235.jsp">4235</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户余额查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4237.jsp">4237</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户银行账户绑定（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4241.jsp">4241</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户银行账户绑定状态查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4244.jsp">4244</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户银行账户解绑（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4245.jsp">4245</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户充值（托管户） </td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4251.jsp">4251</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户充值查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4252.jsp">4252</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户提现（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4255.jsp">4255</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户提现查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4256.jsp">4256</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户扣款签约（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4261.jsp">4261</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户扣款签约查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4262.jsp">4262</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户扣款解约（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4264.jsp">4264</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">扣款/自动投资签约(托管户)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4271.jsp">4271</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">扣款/自动投资解约(托管户)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4273.jsp">4273</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">扣款/自动投资签约查询(托管户)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4276.jsp">4276</a></td>
    </tr>
    
    
    <tr class="mouseout">
        <td width="200" class="content" height="24">自动投资签约</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4320.jsp">4320</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">自动投资签约查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4321.jsp">4321</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">余额查询/扣款/自动投资签约</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4331.jsp">4331</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">余额查询/扣款/自动投资解约</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4333.jsp">4333</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">余额查询/扣款/自动投资签约查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4336.jsp">4336</a></td>
    </tr>
    	
    <tr class="mouseout">
        <td width="200" class="content" height="24">自动投资解约</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4328.jsp">4328</a></td>
    </tr>
    <!-- PaymentAccount End -->
    <tr>
        <td class="head" height="24" colspan="2">支付账户-类银企直连(4500)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户余额查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4510.jsp">4510</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户交易明细查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4512.jsp">4512</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户网银充值</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4520.jsp">4520</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户网银充值查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4524.jsp">4524</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户代收充值</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4526.jsp">4526</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户代收充值查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4528.jsp">4528</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户单笔转账</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4530.jsp">4530</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户单笔转账查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4532.jsp">4532</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户批量转账（支付账户->银行账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4534.jsp">4534</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户批量转账（支付账户->银行账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4536.jsp">4536</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户批量转账交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4538.jsp">4538</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户单笔转账（支付账户->支付账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4540.jsp">4540</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户单笔转帐查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4542.jsp">4542</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户批量转账（支付账户->支付账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4544.jsp">4544</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户批量转账交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4546.jsp">4546</a></td>
    </tr>
    <!-- CrossBorder Start -->
    <tr>
        <td class="head" height="24" colspan="2">跨境支付(5100)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">境外收款方账户</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5111.jsp">5111</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">境外收款方账户查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5116.jsp">5116</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境汇款支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5121.jsp">5121</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境汇款支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5126.jsp">5126</a></td>
    </tr>
    <!-- CrossBorder End -->
    <!-- JSBC Start -->
    <tr>
        <td class="head" height="24" colspan="2">江苏广电(6100)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">短信下发（手机号）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2330.jsp">2330</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">短息下发（支付账号）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2331.jsp">2331</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">基金开户</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6101.jsp">6101</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">开户查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6102.jsp">6102</a></td>
    </tr>
   <tr class="mouseout">
        <td width="200" class="content" height="24">绑卡</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6111.jsp">6111</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">绑卡查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6112.jsp">6112</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">解绑</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6116.jsp">6116</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">充值</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6121.jsp">6121</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">充值查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6122.jsp">6122</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户实时代扣</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6124.jsp">6124</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户实时代扣交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6125.jsp">6125</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">提现</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6126.jsp">6126</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">提现查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6127.jsp">6127</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户资产查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6131.jsp">6131</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">基金参数查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6132.jsp">6132</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">收益明细查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6133.jsp">6133</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户收支查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6134.jsp">6134</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">公告查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6135.jsp">6135</a></td>
    </tr>
    <!-- JSBC End -->
    <tr>
        <td class="head" height="24" colspan="2">招行融资平台接口(7200)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">建立绑定关系</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7201.jsp">7201</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">查询绑定关系</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7202.jsp">7202</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">解除绑定关系</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7203.jsp">7203</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人快捷支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7213.jsp">7213</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人快捷支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7223.jsp">7223</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人订单支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7211.jsp">7211</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7220.jsp">7220</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订单查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7230.jsp">7230</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">白名单上传</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7231.jsp">7231</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">付款人账户信息查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7232.jsp">7232</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7241.jsp">7241</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7242.jsp">7242</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">融资人结算</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7243.jsp">7243</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">融资人结算查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7244.jsp">7244</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人快捷支付退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7246.jsp">7246</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人快捷支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7247.jsp">7247</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订单划转</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7249.jsp">7249</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">融资人支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7251.jsp">7251</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">融资人支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7252.jsp">7252</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资/还款订单单笔代收(代收中间户)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7261.jsp">7261</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资/还款订单单笔代收交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7262.jsp">7262</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">投资代收退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7264.jsp">7264</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资代收退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7265.jsp">7265</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资/还款订单代收（银行签名版）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7267.jsp">7267</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">中间户账户明细查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7271.jsp">7271</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户余额查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7272.jsp">7272</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户明细查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7273.jsp">7273</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量结算</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7281.jsp">7281</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量结算查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7282.jsp">7282</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量结算明细查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7283.jsp">7283</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">银联B2B(7600)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订单支付（银联）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7611.jsp">7611</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">订单支付状态查询（银联）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7620.jsp">7620</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">付款人信息查询（银联）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7621.jsp">7621</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">华夏融资平台接口(7300)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">华夏融资订单支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="HXBPay.jsp">HXBPay</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">华夏融资订单查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="HXBQuery.jsp">HXBQuery</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">华夏融资订单对账</td>
        <td width="*" class="content" height="24"><a target="_blank" href="HXBBill.jsp">HXBBill</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">锦程物流接口</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">代付指令</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6002.jsp">6002</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">监管银行对账接口</td>
        <td width="*" class="content" height="24"><a target="_blank" href="SPDBTx1840.jsp">1840</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">中金宝接口</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">中金宝开户</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4381.jsp">4381</a></td>
    </tr>
	<tr class="mouseout">
        <td width="200" class="content" height="24">统一账户余额查询（原4212）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4212.jsp">4212</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">专属账户余额查询（原4237）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4237.jsp">4237</a></td>
    </tr>
</table>
</body>
</html>
