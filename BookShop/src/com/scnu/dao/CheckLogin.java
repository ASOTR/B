package com.scnu.dao;

import java.sql.ResultSet;

import com.scnu.util.DBHelper;

/**
 * 登陆验证
 */
public class CheckLogin {
	private DBHelper db=new DBHelper();
	//系统管理员登录
	public String checklogin(String userNumber,String password) throws Exception{
		String arg[]={userNumber};
		ResultSet rs=db.query("select password from systemuser where userNumber =?",arg);
	    
		boolean flag=rs.next();
		if(flag==false){return "-1";}
	    while(flag){
	        if(rs.getString("password").equals(password)){
	        	return "1";
	        }
	        flag=rs.next();
	    }
		return "0";
	}
	//会员登录
	public String checkCustomerlogin(String userNumber,String password) throws Exception{
		String arg[]={userNumber};
		ResultSet rs=db.query("select password from user where userNumber =?",arg);
	    
		boolean flag=rs.next();
		if(flag==false){return "-1";}
	    while(flag){
	        if(rs.getString("password").equals(password)){
	        	return "1";
	        }
	        flag=rs.next();
	    }
		return "0";
	}
}
