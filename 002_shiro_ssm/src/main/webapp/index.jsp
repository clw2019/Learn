<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<h2>Hello World!</h2>
<hr>

<%--身份认证标签--%>
<%--已登录--%>
<%--<shiro:authenticated>--%>
<%--    欢迎您，--%>
<%--    <shiro:principal />--%>
<%--    <a href="${pageContext.request.contextPath}/user/logout">登出</a>--%>
<%--</shiro:authenticated>--%>

<%--<shiro:notAuthenticated>--%>
<%--    <a href="${pageContext.request.contextPath}/user/login">请登录</a>--%>
<%--</shiro:notAuthenticated>--%>

<shiro:user>
    欢迎您，
    <shiro:principal />
    <a href="${pageContext.request.contextPath}/user/logout">登出</a>
</shiro:user>
<shiro:guest>
    <a href="${pageContext.request.contextPath}/user/login">请登录</a>
</shiro:guest>

<shiro:guest>
    游客~~
</shiro:guest>
<hr>

<%--角色校验标签--%>
<%--hasRole和lackRole 相当于if和else--%>
<shiro:hasRole name="班长">
    <a href="#">班长操作</a>
</shiro:hasRole>
<shiro:lacksRole name="班长">
    <a href="#">同学操作</a>
</shiro:lacksRole>
<shiro:hasAnyRoles name="班长,同学">
    <a href="#">进班学习</a>
</shiro:hasAnyRoles>
<hr>

<%--权限校验标签--%>
<shiro:hasPermission name="student:yq">
    <a href="#">收罚款</a>
</shiro:hasPermission>
<shiro:lacksPermission name="student:yq">
    <a href="#">进班学习</a>
</shiro:lacksPermission>

<hr>
    ${sessionScope.name}
</body>
</html>
