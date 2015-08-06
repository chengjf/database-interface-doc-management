package com.chengjf.jfinaldemo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInit {

	public static void main(String[] args) {

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:test.db");
			Statement stat = conn.createStatement();
			stat.executeUpdate("drop table if exists people;");
			stat.executeUpdate("create table people (name, occupation);");

			stat.executeUpdate("drop table if exists users;");
			stat.executeUpdate("create table users (username, password);");
			stat.execute("insert into users values('admin', '123456');");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void dbInit(){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:test.db");
			Statement stat = conn.createStatement();
			stat.executeUpdate("drop table if exists people;");
			stat.executeUpdate("create table people (name, occupation);");

			stat.executeUpdate("drop table if exists users;");
			stat.executeUpdate("create table users (username, password);");
			stat.execute("insert into users values('admin', '123456');");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
