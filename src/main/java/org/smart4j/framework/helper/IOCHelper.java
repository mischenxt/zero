package org.smart4j.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectUtil;

public final class IOCHelper {
	static {
		//��ȡ���е�bean����beanʵ��֮���ӳ���ϵ
		Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
		if(CollectionUtil.isNotEmpty(beanMap)) {
			//����beanmap
			for(Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet()){
				//��beanmap�л�ȡbean����ʵ��
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				//��ȡbean�ඨ������г�Ա����
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
