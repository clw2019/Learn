<%--
  Created by IntelliJ IDEA.
  User: 22722
  Date: 2021/1/13
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>用户登录</h1>

    <form action="${pageContext.request.contextPath}/user/login" method="post">
        用户名: <input type="text" name="username"> <br>
        密码: <input type="password" name="password"> <br>
        <input type="submit" value="登录">

    </form>
</body>
</html>
