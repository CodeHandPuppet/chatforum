package com.lxj.chatForum.utils;


import java.sql.*;
import java.util.ResourceBundle;

public class DButils {
    private  static ResourceBundle bundle=ResourceBundle.getBundle("jdbc");
    private  static ThreadLocal<Connection> local=new ThreadLocal<>();
    private static String driver=bundle.getString("jdbc.driver");
    private static String url=bundle.getString("jdbc.url");
    private static String username=bundle.getString("jdbc.username");
    private static String password=bundle.getString("jdbc.password");
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return 数据库链接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn;
        if(local.get() != null){
            conn=local.get();
        }
        else{
            conn=DriverManager.getConnection(url,username,password);
            local.set(conn);
        }

        return conn;
    }

    /**
     *
     * @param conn
     * @param stat
     * @param rs
     */
    public static void close(Connection conn , Statement stat , ResultSet rs){

        if (conn != null) {
            local.remove();
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
