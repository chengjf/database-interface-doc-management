package com.chengjf.jfinaldemo.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.beetl.core.GroupTemplate;

import com.chengjf.jfinaldemo.beetle.MyBeetlRenderFactory;
import com.chengjf.jfinaldemo.controller.AppSystemController;
import com.chengjf.jfinaldemo.controller.IndexController;
import com.chengjf.jfinaldemo.controller.LoginController;
import com.chengjf.jfinaldemo.model.AppSystem;
import com.chengjf.jfinaldemo.model.User;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.Sqlite3Dialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

public class MyConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		loadPropertyFile("config.properties");
		me.setDevMode(getPropertyToBoolean("devMode", true));
		// me.setUploadedFileSaveDirectory(cn.jfinalbbs.common.Constants.UPLOAD_DIR);
		me.setMaxPostSize(2048000);
		// 配置模板
		me.setMainRenderFactory(new MyBeetlRenderFactory());
		me.setViewType(ViewType.JSP);
		me.setEncoding("UTF-8");
	}

	@Override
	public void configHandler(Handlers arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configInterceptor(Interceptors arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configPlugin(Plugins me) {

		dbInit();
		C3p0Plugin c3p0Plugin = new C3p0Plugin("jdbc:sqlite:test.db", "", "");

		c3p0Plugin.setDriverClass("org.sqlite.JDBC");
		me.add(c3p0Plugin);

		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setDialect(new Sqlite3Dialect());
		arp.setShowSql(true);
		me.add(arp);

		arp.addMapping("user", User.class);
		arp.addMapping("app_system", AppSystem.class);
	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		me.add("/", IndexController.class); // 第三个参数为该Controller的视图存放路径
		me.add("/login", LoginController.class); // 第三个参数为该Controller的视图存放路径
		me.add("/system", AppSystemController.class);
	}

	private void dbInit() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:test.db");
			Statement stat = conn.createStatement();
			stat.executeUpdate("drop table if exists people;");
			stat.executeUpdate("create table people (name, occupation);");

			stat.executeUpdate("drop table if exists user;");
			stat.executeUpdate("create table user (username, password);");
			stat.execute("insert into user values('admin', '123456');");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
