package org.smart4j.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ReflectUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectUtil.class);
	/**
	 * 创建实例
	 * @param cls
	 * @return
	 */
	public static Object newInstance(Class<?> cls) {
		Object instance;
		try {
			instance = cls.newInstance();
		} catch (Exception e) {
			LOGGER.error("new intance failure" ,e );
			throw new RuntimeException(e);
		}
		return instance; 
	}
	/**
	 * 调用方法
	 * @param obj
	 * @param method
	 * @param args
	 * @return
	 */
	public static Object invokeMethod(Object obj , Method method, Object... args) {
		Object result ; 
		method.setAccessible(true);
		try {
			result = method.invoke(obj, args);
		} catch (Exception e) {
			LOGGER.error("invoke method failure" ,e );
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public static void setfield(Object obj , Field field ,Object value) {
		field.setAccessible(true);
		try {
			field.set(obj, value);
		} catch (Exception e) {
			LOGGER.error("invoke method failure" ,e );
			throw new RuntimeException(e);
		}
	}
	
	
}
