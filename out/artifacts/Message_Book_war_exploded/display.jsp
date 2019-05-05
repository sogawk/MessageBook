<%@ page import="bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Message" %>
<%@ page import="dao.UserDao" %>
<%@ page import="bean.PageBean" %><%--
  Created by IntelliJ IDEA.
  User: Harry
  Date: 2019/4/20
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User DlUser = (User) request.getSession().getAttribute("DlUser");
    PageBean pageBean = (PageBean) request.getSession().getAttribute("pageBean");
    UserDao userDao = new UserDao();
    User user;
    int i = pageBean.getIndexBegin();
%>
<html>
<style>
    #span1 {
        position: absolute;
        top: 90%;
        left: 30%;
    }


</style>
<head>
    <title>留言本-留言展示</title>
</head>
<body>


<a href="index.jsp">退出登录</a>&nbsp;&nbsp;&nbsp;
<a href="modifyPassword.jsp">修改密码</a>
<center>
    <h3 style="display: inline">
        当前用户：<%=DlUser.getName()%>
    </h3>
</center>
<br>


<h3 style="display: inline"><a href="/addMessage.jsp">新增留言</a></h3>

<form action="/SelectServlet" method="post" style="display: inline ;float: right">
    姓名：<input type="text" name="name">&nbsp;留言: <input type="text" name="message">&nbsp;

    <input type="submit" value="查找">&nbsp;<input type="reset" value="重置">
</form>


<table border="1" width="100%" align="center">

    <tr>
        <th>id</th>
        <th>名字</th>
        <th>描述</th>
        <th>创建时间</th>
        <th>修改时间</th>
        <th>留言内容</th>
        <th>操作</th>
    </tr>

    <%
        for (Message message : (List<Message>) pageBean.getList()) {
            user = userDao.selectId(message.getUserId());
    %>
    <tr>
        <th height="50px"><%=++i%>
        </th>
        <th><%=user.getName()%>
        </th>

        <th><%=message.getDir()%>
        </th>
        <th><%=message.getTime()%>
        </th>
        <th><%=message.getRecentMobi()%>
        </th>
        <th><%=message.getMess()%>
        </th>
        <th><%
            if (DlUser.getId() == message.getUserId()) {
        %>
            <a href="modifyMessage.jsp?messageId=<%=message.getId()%>">修改</a>&nbsp;
            <a href="DeleteMessageServlet?messageId=<%=message.getId()%>">删除</a>&nbsp;
            <%
            } else {
            %>
            不能操作
            <%
                }
            %>
        </th>
    </tr>
    <%
        }
    %>
</table>

<span id="span1">
    <a href="DisplayServlet?pp=1">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="DisplayServlet?pp=<%=pageBean.getCurrentPage()-1%>">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
    当前为第<%=pageBean.getCurrentPage()%>/<%=pageBean.getTotalPage()%>页
    &nbsp;&nbsp;&nbsp;&nbsp;
    <a href="DisplayServlet?pp=<%=pageBean.getCurrentPage()+1%>">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="DisplayServlet?pp=<%=pageBean.getTotalPage()%>">末页</a>&nbsp;&nbsp;&nbsp;&nbsp;
    跳转到：<%
    for (int pp = 1; pp <= pageBean.getTotalPage(); pp++) {
%><a href="DisplayServlet?pp=<%=pp%>"><%=pp%></a> &nbsp;<%
    }
%>
</span>

</body>
</html>
