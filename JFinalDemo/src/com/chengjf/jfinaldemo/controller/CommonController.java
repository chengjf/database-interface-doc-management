package com.chengjf.jfinaldemo.controller;

import com.chengjf.jfinaldemo.common.Constants;
import com.chengjf.jfinaldemo.util.Result;
import com.jfinal.core.Controller;

public class CommonController extends Controller {

	public void logout() {
		removeCookie(Constants.COOKIE_USER_TOKEN);
		removeSessionAttr(Constants.SESSION_USER);
		redirect(Constants.getBaseUrl() + "/");
	}

	public void success() {
        success(null);
    }

    public void success(Object object) {
        renderJson(new Result(Constants.ResultCode.SUCCESS, Constants.ResultDesc.SUCCESS, object));
    }

    public void error(String message) {
        renderJson(new Result(Constants.ResultCode.FAILURE, message, null));
    }
}
