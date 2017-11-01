<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>注册</title>
</head>
<body>
<h1>没有找到该用户,请注册</h1>
<form:form>
    <%--流程执行的key--%>
    <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
    <label for="phone">手机号码:</label>
    <input id="phone" name="phoneNumber" value="${customer.phoneNumber}">
    <label for="area">配送区域</label>
    <input id="area" placeholder="请输入配送区域" name="zipCode">
    <input type="submit" name="_eventId_submit" value="提交">
    <input type="submit" name="_eventId_cancel" value="取消">
</form:form>
</body>
</html>
