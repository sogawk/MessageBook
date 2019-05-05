<%@ page import="bean.Message" %>
<%@ page import="service.MessageService" %><%--
  Created by IntelliJ IDEA.
  User: Harry
  Date: 2019/4/21
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int messageId=Integer.parseInt(request.getParameter("messageId"));
    MessageService messageService=new MessageService();
    String messa=messageService.getMessage(messageId).getMess();
    String dir=messageService.getMessage(messageId).getDir();
%>
<html>
<head>
    <title>修改留言</title>
</head>
<body>

<center><h3>修改留言</h3><br>
    <form action="/ModifyMessageServlet?messageid=<%=messageId%>" method="post">
        描述:<input type="text" name="dirc" value="<%=dir%>"><br>
        留言:<textarea name="message" cols="30" rows="10"><%=messa%></textarea><br>
        <input type="submit" value="提交">&nbsp;<input type="reset" value="重置">
    </form>
</center>

</body>
</html>
