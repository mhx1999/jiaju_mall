package com.hsp.furns.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 韩顺平
 * @version 1.0
 * 基于druid数据库连接池的工具类
 */
public class JDBCUtilsByDruid {

    private static DataSource ds;
    //定义属性ThreadLocal，这里存放一个Connection
    private static ThreadLocal<Connection> threadLocalConn =
            new ThreadLocal<>();




    //在静态代码块完成 ds初始化
    static {
        Properties properties = new Properties();
        try {
            //因为我们是web项目，它的工作目录在out下，文件的加载，需要使用类加载器
            //找到我们的工作目录
            properties.load(JDBCUtilsByDruid.class.getClassLoader().getResourceAsStream("druid.properties"));
          //  properties.load(new FileInputStream("src\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //编写getConnection方法
//    public static Connection getConnection() throws SQLException {
//        return ds.getConnection();
//    }

    /*
从ThreadLocal获取connection，从而保证在同一个connection
     */

    public static Connection getConnection(){
        Connection connection = threadLocalConn.get();
        if(connection == null){  //说明当前的threadLocalConn没有连接
           //就从数据库连接中，取出一个连接放入threadLocalConn

            try {
                connection = ds.getConnection();
                //让当前连接不要自动提交
                connection.setAutoCommit(false);
            } catch (SQLException e) {
               e.printStackTrace();
            }
            threadLocalConn.set(connection);

        }

        return connection;
    }


    //提交事务的方法
    public static void  commit(){

        Connection connection = threadLocalConn.get();
        if (connection!=null){ //保证该连接是有效的

            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    //关闭连接
                    connection.close();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        //当提交后，需要把connection 从 threadLocalConn清除掉，不然会造成threadLocalConn长时间持有该连接，造成未知错误且影响效率

        threadLocalConn.remove();


    }


    //回滚的方法：即撤销connection管理dml操作
public static void rollback() {
    Connection connection = threadLocalConn.get();
    if (connection != null) { //保证该连接是有效的

        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //当提交后，需要把connection 从 threadLocalConn清除掉，不然会造成threadLocalConn长时间持有该连接，造成未知错误且影响效率

    threadLocalConn.remove();

}


    //关闭连接, 老师再次强调： 在数据库连接池技术中，close 不是真的断掉连接
    //而是把使用的Connection对象放回连接池
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
