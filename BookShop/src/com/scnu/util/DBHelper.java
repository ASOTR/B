package com.scnu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 数据库工具类
 */
public class DBHelper {
	private static final String driver="com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/bookshopping?useUnicode=true&characterEncoding=UTF-8";
	private static final String username="root";
	private static final String password="root";
	
	private static Connection conn;
	
	//静态加载驱动
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取数据库连接
	public static Connection getConnection() throws Exception{
		if(conn==null){
			conn=DriverManager.getConnection(url,username,password);
		}
		return conn;
	}
	/**
	 * 查询
	 */
	public ResultSet query(String sql,Object args[]) throws Exception{
		PreparedStatement pstmt=getConnection().prepareStatement(sql);
		for(int i=0;i<args.length;i++){
			pstmt.setObject(i+1, args[i]);
		}
		return pstmt.executeQuery();
	}
	/**
	 * 更新数据，增删改
	 */
	public boolean updatedata(String sql,Object args[]) throws Exception{
		PreparedStatement pstmt=getConnection().prepareStatement(sql);
		for(int i=0;i<args.length;i++){
			pstmt.setObject(i+1, args[i]);
		}
		pstmt.executeUpdate();
		return true;
	}

}
