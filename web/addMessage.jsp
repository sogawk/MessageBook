<%--
  Created by IntelliJ IDEA.
  User: Harry
  Date: 2019/4/20
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>新增留言</title>
    <style>
        textarea {
            vertical-align: top;
        }
    </style>
</head>
<body>
<center><h1>增加留言</h1>
    <hr>
    <form action="/AddMessageServlet" method="post">
        描述:<input type="text" name="dirc"><br>
        留言:<textarea name="message" cols="30" rows="10"></textarea><br>
        <input type="submit" value="提交">&nbsp;&nbsp;&nbsp;<input type="reset" value="重置">
    </form>
</center>

</body>
</html>
