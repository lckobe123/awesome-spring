<%--
  Created by IntelliJ IDEA.
  User: Ternence
  Date: 2017/11/2
  Time: 0:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付</title>
</head>
<body>
<P>确认为支付订单:${order}</P>

<a href="${flowExecutionUrl}&_eventId=payment">确认支付</a>
</body>
</html>
