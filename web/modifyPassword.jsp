<%@ page import="bean.User" %><%--
  Created by IntelliJ IDEA.
  User: Harry
  Date: 2019/4/22
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getSession().getAttribute("DlUser");
%>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
<center>
    <h1>修改密码</h1>
    <hr>
    用户名：<%=user.getName()%>:<br/>
    <form action="ModifyPassword" method="post">
        输入密码：<input type="password" name="password"><br/>
        确认密码：<input type="password" name="repassword"><br/>
        <input type="submit" value="提交">&nbsp;&nbsp;<input type="reset" value="重置">
    </form>
</center>
</body>
</html>
