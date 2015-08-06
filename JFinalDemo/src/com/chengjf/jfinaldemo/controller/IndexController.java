package com.chengjf.jfinaldemo.controller;

import javax.servlet.http.HttpSession;

import com.chengjf.jfinaldemo.common.Constants;
import com.chengjf.jfinaldemo.interceptor.UserInterceptor;
import com.chengjf.jfinaldemo.model.User;
import com.jfinal.aop.Before;

public class IndexController extends CommonController {

	@Before(UserInterceptor.class)
	public void index() {

		HttpSession session = getSession();
		User user = (User) session.getAttribute(Constants.SESSION_USER);
		String username = user.getStr(User.USERNAME);
		setAttr("username", username);

		render("index.html");
	}

}
