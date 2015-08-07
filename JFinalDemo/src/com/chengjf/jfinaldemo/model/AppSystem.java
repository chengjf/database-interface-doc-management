package com.chengjf.jfinaldemo.model;

import java.io.Serializable;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class AppSystem extends Model<AppSystem> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7361061322857634843L;

	public static final String SYSTEM_ID = "id";
	public static final String SYSTEM_NAME = "system_name";
	public static final String SYSTEM_DESC = "system_desc";

	public static final AppSystem me = new AppSystem();

	public List<AppSystem> getAllAppSystems() {
		return AppSystem.me.find("select * from app_system");
	}

	public AppSystem getAppSystemById(String id) {
		return AppSystem.me.findFirst("select * from app_system where id=?",
				id);
	}
	
	public boolean updateSystemById(AppSystem  system){
		AppSystem.me.setAttrs(system);
		return AppSystem.me.update();
	}
	
	public boolean deleteSystemById(AppSystem system){
		AppSystem.me.setAttrs(system);
		return AppSystem.me.delete();
	}
}
