package com.chengjf.jfinaldemo.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.chengjf.jfinaldemo.common.Constants;
import com.chengjf.jfinaldemo.interceptor.UserInterceptor;
import com.chengjf.jfinaldemo.model.AppSystem;
import com.jfinal.aop.Before;

public class AppSystemController extends CommonController {

	private static final Logger logger = Logger
			.getLogger(AppSystemController.class);

	@Before(UserInterceptor.class)
	public void index() {
		List<AppSystem> systemList = AppSystem.me.getAllAppSystems();
//		setAttr("systemList", systemList);
		success(systemList);
		render("index.html");
	}

	
	
	public void find() {
		try {
			String id = getPara("id");
			AppSystem system = AppSystem.me.getAppSystemById(id);
			setAttr("system", system);
			success(system);
		} catch (Exception e) {
			error(Constants.FIND_FAILURE);
		}
	}

	@Before(UserInterceptor.class)
	public void delete() {
		String method = getRequest().getMethod();

		if (method.equalsIgnoreCase(Constants.RequestMethod.POST)) {
			try {
				String delete_id = getPara("id");
				logger.info("Delete AppSystem id=" + delete_id);
				AppSystem.me.deleteById(delete_id);
				success();
			} catch (Exception e) {
				error(Constants.DELETE_FAILURE);
			}
		}
	}

	@Before(UserInterceptor.class)
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

	@Before(UserInterceptor.class)
	public void edit() {
		String method = getRequest().getMethod();
		if (method.equalsIgnoreCase(Constants.RequestMethod.POST)) {
			try {
				AppSystem system = getModel(AppSystem.class, "system");

				if (system.update()) {
					logger.info("Saved " + system.getStr(AppSystem.SYSTEM_ID));
					success("保存成功！");
				} else {
					error(Constants.EDIT_FAILURE);
				}

			} catch (Exception e) {
				logger.error(e);
				error(Constants.EDIT_FAILURE);
			}
		}
	}

}
