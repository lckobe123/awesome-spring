<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<h1>制作pizza</h1>
<form:form>
    <%--流程执行的key--%>
    <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
    <select name="size">
        <option value="大">大</option>
        <option value="小">小</option>
    </select>
    <select name="color">
        <option value="黄色">黄色</option>
        <option value="白色">白色</option>
    </select>
    <input type="submit" name="_eventId_addPizza" value="添加Pizza">
</form:form>

</body>
</html>
