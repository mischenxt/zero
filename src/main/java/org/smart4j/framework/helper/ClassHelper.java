package org.smart4j.framework.helper;

import java.util.Set;

import org.smart4j.framework.util.ClassUtil;

public final class ClassHelper {
	/**
	 * 定义类集合
	 */
	private static final Set<Class<?>> CLASS_SET;
	static {
		String basePackage = ConfigHelper.getAppBasePakage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	/**
	 * 获取应用包名下的所有类
	 */
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	
	
}
