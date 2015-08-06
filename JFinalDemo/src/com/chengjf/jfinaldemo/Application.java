package com.chengjf.jfinaldemo;

import com.jfinal.core.JFinal;

public class Application {

	public static void main(String[] args) {
		
		
		JFinal.start("webapp", 8080, "/", 10);
	}
	
}
