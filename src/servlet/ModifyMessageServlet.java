package servlet;

import bean.PageBean;
import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 修改留言
 */
public class ModifyMessageServlet extends HttpServlet {
    MessageService messageService=new MessageService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int messageid = Integer.parseInt(req.getParameter("messageid"));
            String dirc = req.getParameter("dirc");
            String message = req.getParameter("message");
            messageService.modifyMessage(messageid, dirc, message);
            PageBean pageBean=(PageBean) req.getSession().getAttribute("pageBean");
            req.setAttribute("pp",pageBean.getTotalPage());
            req.getRequestDispatcher("/DisplayServlet").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
