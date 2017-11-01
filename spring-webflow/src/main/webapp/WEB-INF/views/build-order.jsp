<%--
  Created by IntelliJ IDEA.
  User: Ternence
  Date: 2017/10/31
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>customer</title>
</head>
<body>
<h1>开始为用户${order.customer.phoneNumber}构建订单</h1>
<p>${order}</p>
<a href="${flowExecutionUrl}&_eventId=createPizza">制作pizza</a>
<a href="${flowExecutionUrl}&_eventId=checkout">提交订单</a>
<a href="${flowExecutionUrl}&_eventId=cancel">取消</a>
</body>
</html>
