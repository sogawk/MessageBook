package servlet;

import bean.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 注册新用户
 */
public class AddUserServlet extends HttpServlet {

    UserService userService=new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String repassword = req.getParameter("repassword");
            if (password.equals("")) {
                resp.getWriter().write("密码不能为空，注册失败。");
                resp.setHeader("refresh", "2;url=/AddUser.jsp");
            } else {
                if (password.equals(repassword)) {
                    if (userService.addUser(name, password)) {
                        resp.getWriter().write("注册成功,自动登陆中。。。。");
                        int id = userService.selectIn(name, password);
                        User user = new User(id, name, password);
                        HttpSession session = req.getSession();
                        session.setAttribute("DlUser", user);
                        resp.setHeader("refresh", "2;url=/DisplayServlet");
                    } else {
                        resp.getWriter().write("用户名被占用，注册失败。");
                        resp.setHeader("refresh", "2;url=AddUser.jsp");
                    }
                } else {
                    resp.getWriter().write("两次密码输入不一致，注册失败。");
                    resp.setHeader("refresh", "2;url=AddUser.jsp");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
