<%--
  Created by IntelliJ IDEA.
  User: Ternence
  Date: 2017/11/1
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>配送区域警告</title>
</head>
<body>
<h1>该区域不能配送,是否接受到店自取?</h1>
<a href="${flowExecutionUrl}&_eventId=accept">接 受</a>
<a href="${flowExecutionUrl}&_eventId=cancel">不接受</a>
</body>
</html>
