package org.smart4j.framework.helper;

import java.util.Properties;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.util.PropsUtil;

public final class ConfigHelper {
	private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
	
	/**
	 * ��ȡjdbc����
	 */
	public static String getJdbcDriver() {
		return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
	}
	/**
	 * 
	 * @return jdbcurl
	 */
	public static String getJdbcUrl() {
		return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_URL);
	}
	
	/**
	 * 
	 * @return jdbc usernmae
	 */
	public static String getJdbcUsername() {
		return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_USERNAME);
	}
	
	public static String getAppBasePakage() {
		return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_BASE_PACKAGE);
	}
	
	public static String getAppJspPath() {
		return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_JSP_PATH);
	}
	
	public static String getAppAssetPath() {
		return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_ASSET_PATH);
	}
	
}
