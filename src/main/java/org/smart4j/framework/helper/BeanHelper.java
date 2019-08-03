package org.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4j.framework.util.ReflectUtil;

public final class BeanHelper {
	/**
	 * ����beanӳ��
	 */
	private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<>();
	static {
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for(Class<?> beanClass : beanClassSet) {
			Object obj = ReflectUtil.newInstance(beanClass);
			BEAN_MAP.put(beanClass, obj);
		}
	}
	/**
	 * ��ȡbeanӳ��
	 */
	public static Map<Class<?>,Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	 * ��ȡbeanʵ��
	 */
	@SuppressWarnings("unchecked")
	public static <T>T getBean(Class<T>cls){
		if(!BEAN_MAP.containsKey(cls)) {
			throw new RuntimeException("can not bean by class"+cls);
		}
		return (T) BEAN_MAP.get(cls);
	}
	
	
}
