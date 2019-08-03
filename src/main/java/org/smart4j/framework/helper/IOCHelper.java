package org.smart4j.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectUtil;

public final class IOCHelper {
	static {
		//获取所有的bean类与bean实例之间的映射关系
		Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
		if(CollectionUtil.isNotEmpty(beanMap)) {
			//遍历beanmap
			for(Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet()){
				//从beanmap中获取bean类与实例
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				//获取bean类定义的所有成员变量
				Field[] beanFields = beanClass.getDeclaredFields();
				if(ArrayUtils.isNotEmpty(beanFields)) {
					for(Field beanField:beanFields) {
						if(beanField.isAnnotationPresent(Inject.class)) {
							Class<?> beanFieldClass = beanField.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if(beanFieldInstance != null) {
								ReflectUtil.setfield(beanInstance, beanField, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}
}
