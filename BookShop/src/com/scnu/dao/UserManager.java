package com.scnu.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.scnu.po.Book;
import com.scnu.po.User;
import com.scnu.util.DBHelper;

/**
 * 用户信息工具类
 */
public class UserManager {
	private DBHelper db=new DBHelper();
	/**
	 * 传入一个用户对象添加用户
	 * @param user 用户
	 */
	public boolean addUser(User user) throws Exception{
		String userName=user.getUserName();
		String userNumber=user.getUserNumber();
		String password=user.getPassword();
		int authority=user.getAuthority();
		String table=selectTable(authority);
		Object arg[]={userName,userNumber,password,authority};
		String sql="insert into "+table+" (userName,userNumber,password,authority) values(?,?,?,?)";
		db.updatedata(sql, arg);
		return true;
	}
	/**
	 * 根据列名和值，（权限区分会员表）删除一行用户
	 * @param colName
	 * @param value
	 * @param authority
	 */
	public boolean delUser(String colName,Object value,int authority) throws Exception{
		String table=selectTable(authority);
		String sql="delete from "+table+" where "+colName+" =?";
		Object arg[]={value};
		db.updatedata(sql, arg);
		return true;
	}
	/**
	 * 更新用户信息 
	 * @param user 用户对象
	 * @param id 用户在数据库表中的id号
	 */
	public boolean updateUser(User user,int id) throws Exception{
		String userName=user.getUserName();
		String userNumber=user.getUserNumber();
		String password=user.getPassword();
		int authority=user.getAuthority();
		String table=selectTable(authority);
		Object arg[]={id,userName,userNumber,password,authority,id};
		String sql="update "+table+" set id=?,userName=?,userNumber=?,password=?,authority=? where id=?";
		db.updatedata(sql, arg);
		return true;
	}
	/**
	 * 查询单个用户
	 * @param colName 列名
	 * @param value 值
	 * @param authority 权限
	 */
	public User selectUser(String colName,Object value,int authority) throws Exception{
		String table=selectTable(authority);
		Object arg[]={value};
		String sql="select * from "+table+" where "+colName+"=?";
		ResultSet rs=db.query(sql, arg);
		String userName=null;
		String userNumber=null;
		String password=null;
		int id=0;
		while(rs.next()){
			id=rs.getInt("id");
			userName=rs.getString("userName");
			userNumber=rs.getString("userNumber");
			password=rs.getString("password");
			authority=rs.getInt("authority");
		}
		User user=new User(id,userName,userNumber,password,authority);
		return user;
	}
	/**
	 * 查询会员或系统管理员表
	 * @param authority
	 */
	public ArrayList<User> selectUsers(int authority) throws Exception{
		ArrayList<User> Userlist=new ArrayList<User>();
		String table=selectTable(authority);
		String sql="select * from "+table+"";
		Object arg[]={};
		ResultSet rs=db.query(sql, arg);
		String userName=null;
		String userNumber=null;
		String password=null;
		int id=0;
		while(rs.next()){
			id=rs.getInt("id");
			userName=rs.getString("userName");
			userNumber=rs.getString("userNumber");
			password=rs.getString("password");
			authority=rs.getInt("authority");
			User user=new User(id,userName,userNumber,password,authority);
			Userlist.add(user);
		}
		return Userlist;
	}
	/**
	 * 根据权限选择 会员表和系统管理员表
	 * @param authority 权限
	 */
	public String selectTable(int authority){
		String table=null;
		if(authority==0){//普通会员
			table="user";
		}else if(authority==1){//系统管理员
			table="systemuser";
		}
		return table;
	}
//	public static void main(String[] args) throws Exception {
//		UserManager um=new UserManager();
//		User user=new User("莫小平4","2012004","123456",0);
//		um.addUser(user);
//		um.delUser("userNumber", "2012000", 0);
//		um.updateUser(user, 2);
//		ArrayList<User> Userlist=new ArrayList<User>();
//		Userlist=um.selectUsers(0);
//		System.out.println(Userlist.get(0).getUserName());
//	}
}
