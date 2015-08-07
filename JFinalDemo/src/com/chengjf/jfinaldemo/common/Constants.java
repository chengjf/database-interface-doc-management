package com.chengjf.jfinaldemo.common;

import com.jfinal.kit.PropKit;

public class Constants {

	// Page use
	public final static String LOGIN_ERROR = "error";

	// Session
	public final static String SESSION_USER = "user";

	// Cookie
	public final static String COOKIE_USER_TOKEN = "cookie_user_token";
	public static final String COOKIE_USER_SEPARATOR = "@&@";

	// URL
	public final static String BEFORE_URL = "before_url";

	
	public static final String DELETE_FAILURE = "删除失败";
	public static final String FIND_FAILURE = "查找失败";
	public static final String EDIT_FAILURE = "编辑失败";
	
	
	public static String getBaseUrl() {
		return PropKit.use("config.properties").get("base.url");
	}

	public static class RequestMethod {
		public static final String GET = "get";
		public static final String POST = "post";
		public static final String PUT = "PUT";
		public static final String DELETE = "DELETE";
	}
	
	public static class ResultCode {
        public static final String SUCCESS = "200";
        public static final String FAILURE = "201";
    }

    public static class ResultDesc {
        public static final String SUCCESS = "success";
        public static final String FAILURE = "error";
    }
}

