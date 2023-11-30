package com.xmut.estore.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xmut.estore.exception.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * JDBC 的工具类
 *
 */
public class JDBCUtils {

    private static ComboPooledDataSource dataSource;

   static{
       try{
            dataSource = new ComboPooledDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("123456");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/no30_estore?useUnicode=true&characterEncoding=utf-8");
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setInitialPoolSize(2);
            dataSource.setAcquireIncrement(5);
            dataSource.setMinPoolSize(5);
            dataSource.setMaxPoolSize(10);
            dataSource.setMaxStatements(20);
            dataSource.setMaxIdleTime(500000);
            dataSource.setIdleConnectionTestPeriod(5);
            dataSource.setAcquireRetryAttempts(20);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	//获取数据库连接
	public static Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("数据库连接错误!");
		}
	}

	//关闭数据库连接
	public static void release(Connection connection) {
		try {
			if(connection != null){
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("数据库连接错误!");
		}
	}

	//关闭数据库连接
	public static void release(ResultSet rs, Statement statement) {
		try {
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("数据库连接错误!");
		}

		try {
			if(statement != null){
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("数据库连接错误!");
		}
	}

}
