package servlet;

import bean.Message;
import bean.PageBean;
import dao.MessageDao;
import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 展示留言
 */
public class DisplayServlet extends HttpServlet {
    MessageService messageService=new MessageService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {


            PageBean pageBean = new PageBean();
            pageBean.setTotalData(messageService.totalData());
            pageBean.setPageData(10);
            pageBean.setCurrentPage(1);


            if(req.getParameter("pp")!=null||req.getAttribute("pp")!=null){
                String temp=req.getParameter("pp");

                        String aa=((temp==null)? String.valueOf(req.getAttribute("pp")) :temp);
                Integer pp=Integer.parseInt(aa);
                pageBean=(PageBean) req.getSession().getAttribute("pageBean");

                if(pp>pageBean.getTotalPage()||pp<1)pp=pageBean.getCurrentPage();
                pageBean.setCurrentPage(pp);
            }




            messageService.display(pageBean);
            HttpSession session=req.getSession();
            session.setAttribute("pageBean",pageBean);
            req.getRequestDispatcher("display.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
