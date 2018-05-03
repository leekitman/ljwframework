package com.leekitman.practise.test.threadlocal.two;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author LeeKITMAN
 * @see 2018/5/3 17:57
 */
public class DBUtil {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://192.168.40.128:3306/ljw_framework?useUnicode=true&characterEncoding=utf8&useSSL=false";
    private static final String username = "lkmdb";
    private static final String password = "lkm@Root678";

    /**
     * 定义一个数据库连接
     */
    private static ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection conn = connContainer.get();
        try {
            if(conn == null){
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connContainer.set(conn);
        }
        return conn;
    }

    /**
     * 关闭连接
     */
    public static void closeConnection() {
        Connection conn = connContainer.get();
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connContainer.remove();
        }
    }
}
