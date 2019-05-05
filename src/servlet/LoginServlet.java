package servlet;

import bean.User;
import service.UserService;
import util.CheckUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 用户登录
 */
public class LoginServlet extends HttpServlet {
    UserService userService=new UserService();
    CheckUtil checkUtil=new CheckUtil();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String name = req.getParameter("name");
            String password = req.getParameter("password");
            int id = checkUtil.loginCheck(name, password);
            if(id==-1){
                resp.getWriter().write("用户名或密码错误，登陆失败....2秒自动后返回登陆界面\n");
                resp.setHeader("refresh", "2;url=index.jsp");
            }
            else {
                User user = new User(id, name, password);
                HttpSession session = req.getSession();
                session.setAttribute("DlUser", user);
                req.getRequestDispatcher("/DisplayServlet").forward(req, resp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
