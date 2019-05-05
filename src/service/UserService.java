package service;

import bean.User;
import dao.UserDao;

public class UserService {
    UserDao userDao=new UserDao();
    public int selectIn(String name,String password) throws Exception {
        return userDao.selectIn(name,password);
    }

    public User getUser(int id) throws Exception {
        return userDao.selectId(id);
    }

    public Boolean addUser(String name,String password) throws Exception {
         if(userDao.selectName(name))return false;
         return userDao.insert(name,password);

    }

    public void updatePassword(User user,String password) throws Exception {
        userDao.updatePassword(user,password);
    }
}
