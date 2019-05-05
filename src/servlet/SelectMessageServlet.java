package servlet;

import bean.PageBean;
import bean.SelectObjBean;
import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SelectMessageServlet extends HttpServlet {
    MessageService messageService = new MessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PageBean pageBean = (PageBean) req.getSession().getAttribute("pageBean");
            String name = req.getParameter("name");
            String message = req.getParameter("message");
            SelectObjBean selectObjBean = new SelectObjBean(name, message);

            if (req.getParameter("pp") != null) {
                int pp = Integer.parseInt(req.getParameter("pp"));
                selectObjBean = (SelectObjBean) req.getSession().getAttribute("selectObj");

                if (pp > pageBean.getTotalPage() || pp < 1) pp = pageBean.getCurrentPage();
                pageBean.setCurrentPage(pp);
            }
            pageBean.setTotalData(messageService.selectNum(selectObjBean));

            messageService.select(pageBean, selectObjBean);

            HttpSession session = req.getSession();
            session.setAttribute("selectObj", selectObjBean);
            req.getRequestDispatcher("SelectDisplay.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
