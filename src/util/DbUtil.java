package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public Connection getConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/message?characterEncoding=utf-8","root","123456");
    }
    public void closeAll(AutoCloseable...autoCloseables) throws Exception {
        for (AutoCloseable autoCloseable : autoCloseables) {
            if(autoCloseable!=null){
                autoCloseable.close();
            }
        }
    }
}
