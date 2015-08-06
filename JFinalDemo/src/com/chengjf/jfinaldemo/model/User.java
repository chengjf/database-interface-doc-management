package com.chengjf.jfinaldemo.model;

import java.io.Serializable;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8575961826888215152L;
	
	
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	
	public static final User me = new User();

	public User findByUserName(String username) {
		String sql = "select u.* from user u where u.username=?";
		return super.findFirst(sql, username);
	}

	public User login(String username, String password) {
		String sql = "select * from user where username=? and password=?";
		return super.findFirst(sql, username, password);
	}

}
