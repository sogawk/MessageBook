<%--
  Created by IntelliJ IDEA.
  User: Harry
  Date: 2019/4/21
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<center><h1>注册</h1>
    <form action="/AddUserServlet" method="post">
        用户名:<input type="text" name="name"><br/>
        密码：<input type="password" name="password"><br/>
        确认密码：<input type="password" name="repassword"><br/><br/>
        <input type="submit" value="提交"> &nbsp; <input type="reset" value="重置">&nbsp;&nbsp;<button><a href="/index.jsp">返回登陆页面</a>
    </button>
    </form>
</center>
</body>
</html>
