package servlet;

import bean.PageBean;
import bean.User;
import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 增加留言
 */
public class AddMessageServlet extends HttpServlet {
    MessageService messageService=new MessageService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PageBean pageBean=(PageBean) req.getSession().getAttribute("pageBean");
            String dirc = req.getParameter("dirc");
            String message = req.getParameter("message");
            User dlUser = (User) req.getSession().getAttribute("DlUser");
            messageService.addMessage(dlUser, message, dirc);
            pageBean.setTotalData(messageService.totalData());
            req.setAttribute("pp",pageBean.getTotalPage());
            req.getRequestDispatcher("/DisplayServlet").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
