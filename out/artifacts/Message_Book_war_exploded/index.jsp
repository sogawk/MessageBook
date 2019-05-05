<%--
  Created by IntelliJ IDEA.
  User: Harry
  Date: 2019/4/20
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆界面</title>
</head>
<body>
<center>
    <form action="/LoginServlet" method="post">
        <h1>留言本</h1>
            请输入用户名：<input type="text" name="name"><br/>
            请输入密码：<input type="password" name="password"><br/><br/>
            <input type="submit" value="登陆">&nbsp;&nbsp;<input type="reset" value="重置">&nbsp;&nbsp;<button><a
                    href="AddUser.jsp">注册</a></button>
    </form>
</center>
</body>
</html>
