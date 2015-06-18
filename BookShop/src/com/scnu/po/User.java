package com.scnu.po;
/**
 * 用户类
 * 系统管理员表：systemuser
 * 会员用户表：user
 * authority 0会员 1系统管理员
 */
public class User {
	private int id;
	private String userName;//用户名
	private String userNumber;//用户账号
	private String password;//用户密码
	private int authority;//用户权限
	public User(){}
	public User(String userName, String userNumber, String password,
			int authority) {
		super();
		this.userName = userName;
		this.userNumber = userNumber;
		this.password = password;
		this.authority = authority;
	}
	public User(int id,String userName, String userNumber, String password,
			int authority) {
		super();
		this.id = id;
		this.userName = userName;
		this.userNumber = userNumber;
		this.password = password;
		this.authority = authority;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	
}
