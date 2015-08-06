package com.chengjf.jfinaldemo.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.chengjf.jfinaldemo.common.Constants;
import com.chengjf.jfinaldemo.interceptor.UserInterceptor;
import com.chengjf.jfinaldemo.model.AppSystem;
import com.chengjf.jfinaldemo.model.User;
import com.jfinal.aop.Before;

public class AppSystemController extends CommonController {

	private static final Logger logger = Logger
			.getLogger(AppSystemController.class);

	@Before(UserInterceptor.class)
	public void index() {
		String method = getRequest().getMethod();
		List<AppSystem> systemList = AppSystem.me.getAllAppSystems();
		setAttr("systemList", systemList);
		render("index.html");
	}

	public void delete() {
		String method = getRequest().getMethod();

		if (method.equalsIgnoreCase(Constants.RequestMethod.POST)) {
			try {
				String delete_id = getPara("delete_id");
				AppSystem.me.deleteById(delete_id);
				success();
			} catch (Exception e) {
				error(Constants.DELETE_FAILURE);
			}
		}
		// redirect(Constants.getBaseUrl() + "/system");
	}

	public void add() {
		String method = getRequest().getMethod();
		if (method.equalsIgnoreCase(Constants.RequestMethod.POST)) {
			String id = getPara("id");
			String name = getPara("name");
			String desc = getPara("desc");
			AppSystem system = new AppSystem();
			system.set(AppSystem.SYSTEM_ID, id)
					.set(AppSystem.SYSTEM_NAME, name)
					.set(AppSystem.SYSTEM_DESC, desc).save();
		}
		redirect(Constants.getBaseUrl() + "/system");
	}

}
