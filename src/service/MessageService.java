package service;

import bean.Message;
import bean.PageBean;
import bean.SelectObjBean;
import bean.User;
import dao.MessageDao;

import java.util.List;

public class MessageService {
    MessageDao messageDao=new MessageDao();
    public PageBean display(PageBean pageBean) throws Exception {
        return messageDao.jump(pageBean);
    }

    public int totalData() throws Exception {
        return messageDao.totalData();
    }

    public void addMessage(User user,String message,String dir) throws Exception {
        messageDao.addMessage(user,message,dir);
    }

    public void modifyMessage(int messageId,String dir,String message) throws Exception {
        messageDao.modify(messageId,dir,message);
    }

    public void delete(int messageId) throws Exception {
        messageDao.delete(messageId);
    }

    public Message getMessage(int messageId) throws Exception {
        return messageDao.getMessage(messageId);
    }

    public int selectNum(SelectObjBean selectObjBean) throws Exception {
        return messageDao.selectMessageNum(selectObjBean);
    }

    public void select(PageBean pageBean,SelectObjBean selectObjBean) throws Exception {
         messageDao.selectMessage(pageBean,selectObjBean);
    }

}
