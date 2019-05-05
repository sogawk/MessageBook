package servlet;

import bean.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModifyPasswordServlet extends HttpServlet {
    UserService userService=new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user=(User) req.getSession().getAttribute("DlUser");
            String password = req.getParameter("password");
            userService.updatePassword(user,password);
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
