package com.yunsi.shop.bean;
/**
 * 用户对象 基本属性 ：用户id 用户名 用户密码
 * @author ShenBL
 *
 */

public class UserBean {
	private String uid;
	private String username;
	private String password;
	public UserBean() {
		super();
	}

	public UserBean( String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}
