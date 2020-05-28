<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<h2>Hello World!</h2>
<hr>

<%--身份认证标签--%>
<%--已登录--%>
<shiro:authenticated>
    欢迎您，
    <shiro:principal />
    <a href="${pageContext.request.contextPath}/user/logout">登出</a>
</shiro:authenticated>
<shiro:notAuthenticated>
    <a href="${pageContext.request.contextPath}/user/login">请登录</a>
</shiro:notAuthenticated>
<shiro:guest>
    游客~~
</shiro:guest>
<hr>

<%--角色校验标签--%>
<%--hasRole和lackRole 相当于if和else--%>
<shiro:hasRole name="admin">
    <a href="#">到财务取钱</a>
</shiro:hasRole>
<shiro:lacksRole name="admin">
    <a href="#">等财务发工资</a>
</shiro:lacksRole>
<shiro:hasAnyRoles name="admin,manager">
    <a href="#">招聘讲师</a>
</shiro:hasAnyRoles>
<hr>

<%--权限校验标签--%>
<shiro:hasPermission name="user:query">
    <a href="#">所有用户</a>
</shiro:hasPermission>
<shiro:lacksPermission name="user:query">
    <a href="#">个人信息</a>
</shiro:lacksPermission>
</body>
</html>
