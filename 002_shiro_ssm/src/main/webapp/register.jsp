<%--
  Created by IntelliJ IDEA.
  User: clw
  Date: 2020/6/1
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/register" method="post">
        username: <input type="text" name="username"> <br>
        password: <input type="text" name="password"> <br>
        <input type="submit" value="注册">
    </form>
</body>
</html>
