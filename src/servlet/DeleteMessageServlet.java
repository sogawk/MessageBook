package servlet;

import bean.PageBean;
import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除留言
 */
public class DeleteMessageServlet extends HttpServlet {
    MessageService messageService=new MessageService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            int messageid = Integer.parseInt(req.getParameter("messageId"));
            messageService.delete(messageid);
            PageBean pageBean=(PageBean) req.getSession().getAttribute("pageBean");
            pageBean.setTotalData(messageService.totalData());
            req.setAttribute("pp",pageBean.getCurrentPage());
            req.getRequestDispatcher("/DisplayServlet").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
