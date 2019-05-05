package dao;

import bean.Message;
import bean.User;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    DbUtil dbUtil = new DbUtil();

    /**
     * 登陆时查找数据库中是否有此用户名与密码
     *
     * @param name
     * @param password
     * @return
     * @throws Exception
     */
    public int selectIn(String name, String password) throws Exception {

        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connect = dbUtil.getConnect();
            String sql = "select id from user where userName=? and password=?";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return -1;
        } finally {
            dbUtil.closeAll(resultSet, preparedStatement, connect);
        }

    }

    /**
     * 根据id返回当前用户对象
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public User selectId(int userId) throws Exception {
        User user;

        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connect = dbUtil.getConnect();
            String sql = "select id ,userName,password from user where id=?";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("userName"), resultSet.getString("password"));
            }
            return null;
        } finally {
            dbUtil.closeAll(resultSet, preparedStatement, connect);
        }

    }

    /**
     * 注册时查找用户名是否被占用
     *
     * @param name
     * @return 被占用返回true
     * @throws Exception
     */
    public Boolean selectName(String name) throws Exception {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connect = dbUtil.getConnect();
            String sql = "select id from user where userName=?";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
            return false;
        } finally {
            dbUtil.closeAll(resultSet, preparedStatement, connect);
        }
    }

    /**
     * 注册时向数据库中插入用户
     *
     * @param name
     * @param password
     * @throws Exception
     */
    public Boolean insert(String name, String password) throws Exception {
        Connection connect = null;
        PreparedStatement preparedStatement = null;

        try {
            connect = dbUtil.getConnect();
            String sql = "insert into user (userName,password) values (?,?)";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            return true;
        } finally {
            dbUtil.closeAll(preparedStatement, connect);
        }

    }

    public void updatePassword(User user,String password) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connect = null;
        try {
            connect = dbUtil.getConnect();
            String sql = "update  user set password=? where id=?";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } finally {
            dbUtil.closeAll(preparedStatement, connect);
        }
    }


}
