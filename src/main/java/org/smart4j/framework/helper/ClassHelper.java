package org.smart4j.framework.helper;

import java.util.Set;

import org.smart4j.framework.util.ClassUtil;

public final class ClassHelper {
	/**
	 * �����༯��
	 */
	private static final Set<Class<?>> CLASS_SET;
	static {
		String basePackage = ConfigHelper.getAppBasePakage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	/**
	 * ��ȡӦ�ð����µ�������
	 */
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	
	
}
