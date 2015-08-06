package com.chengjf.jfinaldemo.interceptor;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.chengjf.jfinaldemo.common.Constants;
import com.chengjf.jfinaldemo.model.User;
import com.chengjf.jfinaldemo.util.StrUtil;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class UserInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		HttpServletRequest request = inv.getController().getRequest();
		HttpSession session = inv.getController().getSession();

		String userToken = inv.getController().getCookie(
				Constants.COOKIE_USER_TOKEN);

		User user = (User) session.getAttribute(Constants.SESSION_USER);

		if (StrUtil.isBlank(userToken) && user != null) {
			inv.getController().setCookie(
					Constants.COOKIE_USER_TOKEN,
					StrUtil.getEncryptionToken(user.get(User.USERNAME)
							+ Constants.COOKIE_USER_SEPARATOR
							+ user.get(User.PASSWORD)), 30 * 60 * 60 * 24);
		} else if (!StrUtil.isBlank(userToken) && user == null) {
			String[] strs = StrUtil.getDecryptToken(userToken).split(
					Constants.COOKIE_USER_SEPARATOR);
			user = User.me.login(strs[0], strs[1]);
			session.setAttribute(Constants.SESSION_USER, user);
		}

		if (StrUtil.isBlank(userToken) && user == null) {
			String uri = request.getRequestURI();
			String param = "";

			if (request.getQueryString() != null) {
				try {
					param = new String(request.getQueryString().getBytes(
							"ISO8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO: handle exception
				}
			}

			if (!StrUtil.isBlank(param)) {
				uri += "?" + param;
			}
			session.setAttribute(Constants.BEFORE_URL, uri);
			inv.getController().redirect(Constants.getBaseUrl() + "/login");
		} else {
			inv.invoke();
		}

	}
}
