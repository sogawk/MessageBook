package dao;

import bean.Message;
import bean.PageBean;
import bean.SelectObjBean;
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

public class MessageDao {
    DbUtil dbUtil = new DbUtil();

    /**
     * 跳转到指定页面
     *
     * @param pageBean
     * @return
     * @throws Exception
     */
    public PageBean jump(PageBean pageBean) throws Exception {
        Message message;
        List<Message> list = new ArrayList();
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connect = dbUtil.getConnect();
            String sql = "select id,dir,time,recentMobi,mess,userId from messa limit ?,?";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, pageBean.getIndexBegin());
            preparedStatement.setInt(2, pageBean.getindexNum());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                message = new Message(resultSet.getInt("id"), resultSet.getString("time"), resultSet.getString("recentMobi"), resultSet.getString("mess"), resultSet.getInt("userId"), resultSet.getString("dir"));
                list.add(message);
            }
            pageBean.setList(list);
            return pageBean;

        } finally {
            dbUtil.closeAll(resultSet, preparedStatement, connect);
        }
    }

    public int totalData() throws Exception {

        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int num = 0;
        try {
            connect = dbUtil.getConnect();
            String sql = "select id from messa";
            System.out.println(sql);
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                num++;
            }
            return num;

        } finally {
            dbUtil.closeAll(resultSet, preparedStatement, connect);
        }
    }

    public void addMessage(User user, String message, String dirc) throws Exception {
        Connection connect = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        try {
            connect = dbUtil.getConnect();
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
            String sql1 = "alter table message.messa auto_increment 1";
            String sql2 = "insert into messa (time,mess,userId,dir) values (?,?,?,?)";
            preparedStatement1 = connect.prepareStatement(sql1);
            preparedStatement2 = connect.prepareStatement(sql2);
            preparedStatement2.setString(1, time);
            preparedStatement2.setString(2, message);
            preparedStatement2.setInt(3, user.getId());
            preparedStatement2.setString(4, dirc);
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
        } finally {
            dbUtil.closeAll(preparedStatement2, preparedStatement1, connect);
        }

    }

    public void modify(int messageId, String dir, String message) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connect = null;
        try {
            String recentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
            connect = dbUtil.getConnect();
            String sql = "update messa set dir=?,mess=?,recentMobi=? where id=?";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, dir);
            preparedStatement.setString(2, message);
            preparedStatement.setString(3, recentTime);
            preparedStatement.setInt(4, messageId);
            preparedStatement.executeUpdate();
        } finally {
            dbUtil.closeAll(preparedStatement, connect);
        }
    }

    public void delete(int messageId) throws Exception {
        Connection connect = null;
        PreparedStatement preparedStatement = null;

        try {
            connect = dbUtil.getConnect();
            String sql = "delete from messa where id=?";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, messageId);
            preparedStatement.executeUpdate();
        } finally {
            dbUtil.closeAll(preparedStatement, connect);
        }
    }

    public Message getMessage(int messageId) throws Exception {
        Message message = null;
        Connection connect = null;
        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;
        try {
            connect = dbUtil.getConnect();
            String sql = "select * from messa where id=?";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, messageId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                message = new Message(resultSet.getInt("id"), resultSet.getString("time"), resultSet.getString("recentMobi"), resultSet.getString("mess"), resultSet.getInt("userId"), resultSet.getString("dir"));
            }
            return message;
        } finally {
            dbUtil.closeAll(resultSet, preparedStatement, connect);
        }
    }

    public int selectMessageNum(SelectObjBean selectObjBean) throws Exception {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connect = dbUtil.getConnect();
            String sql = "select count(*) as num from messa,user where messa.userId=user.id And user.userName like \"%\"?\"%\" AND messa.mess like \"%\"?\"%\"";
            System.out.println(sql);
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, selectObjBean.getName());
            preparedStatement.setString(2, selectObjBean.getMessage());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("num");
            }
            return 0;

        } finally {
            dbUtil.closeAll(resultSet, preparedStatement, connect);
        }
    }

    public void selectMessage(PageBean pageBean,SelectObjBean selectObjBean) throws Exception {
        Message message;
        List<Message> list = new ArrayList();
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connect = dbUtil.getConnect();
            String sql = "select messa.id,dir,time,recentMobi,mess,userId from messa,user where messa.userId=user.id And user.userName like \"%\"?\"%\" AND messa.mess like \"%\"?\"%\" limit ?,?";
            System.out.println(sql);
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, selectObjBean.getName());
            preparedStatement.setString(2, selectObjBean.getMessage());
            preparedStatement.setInt(3,pageBean.getIndexBegin());
            preparedStatement.setInt(4,pageBean.getindexNum());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                message = new Message(resultSet.getInt("id"), resultSet.getString("time"), resultSet.getString("recentMobi"), resultSet.getString("mess"), resultSet.getInt("userId"), resultSet.getString("dir"));
                list.add(message);
            }
            pageBean.setList(list);


        } finally {
            dbUtil.closeAll(resultSet, preparedStatement, connect);
        }
    }
}