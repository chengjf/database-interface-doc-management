package com.chengjf.jfinaldemo.controller;

import org.apache.log4j.Logger;

import com.chengjf.jfinaldemo.common.Constants;
import com.chengjf.jfinaldemo.model.User;
import com.chengjf.jfinaldemo.util.StrUtil;

public class LoginController extends CommonController {

	public void index() {

		String method = getRequest().getMethod();

		if (method.equalsIgnoreCase(Constants.RequestMethod.GET)) {
			Logger.getLogger(this.getClass()).info("login get");
			User user = getSessionAttr(Constants.SESSION_USER);
			if (user != null) {
				redirect(Constants.getBaseUrl() + "/index");
			} else {
				render("index.html");
			}
		} else if (method.equalsIgnoreCase(Constants.RequestMethod.POST)) {
			Logger.getLogger(this.getClass()).info("login post");
			String username = getPara("username");
			String password = getPara("password");

			User user = User.me.login(username, password);

			if (user == null) {
				setAttr(Constants.LOGIN_ERROR, "用户名或密码错误");
				render("index.html");
			} else {
				int remember_me = getParaToInt("remember_me", 0);
				setSessionAttr(Constants.SESSION_USER, user);
				if (remember_me == 1) {
					setCookie(
							Constants.COOKIE_USER_TOKEN,
							StrUtil.getEncryptionToken(username
									+ Constants.COOKIE_USER_SEPARATOR
									+ password), 30 * 24 * 60 * 60);
				}
				String before_url = getSessionAttr(Constants.BEFORE_URL);
				if (!StrUtil.isBlank(before_url)
						&& !before_url.contains("login")) {
					redirect(before_url);
				}
				redirect(Constants.getBaseUrl() + "/index");
			}

		}
	}
}
