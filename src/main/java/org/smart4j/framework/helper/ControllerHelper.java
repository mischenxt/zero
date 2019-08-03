package org.smart4j.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.CollectionUtil;

public class ControllerHelper {
	private static final Map<Request,Handler> ACTION_MAP = new HashMap<>();
	static {
		//��ȡ���е�controller��
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if(CollectionUtil.isNotEmpty(controllerClassSet)) {
			for(Class<?> controllerClass : controllerClassSet) {
				//��ȡcontroller���ж���ķ���
				Method[] methods = controllerClass.getDeclaredMethods();
				if(ArrayUtils.isNotEmpty(methods)) {
					//������Щcontroller���еķ���
					for(Method  method :methods ) {
						if(method.isAnnotationPresent(Action.class)) {
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							if(mapping.matches("\\w+:/\\w*")) {
								String[] array = mapping.split(":");
								if(ArrayUtils.isNotEmpty(array)&& array.length==2) {
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod,requestPath);
									Handler handler = new Handler(controllerClass,method);
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static Handler getHandler(String requestMethod, String requestPath) {
		Request reuqest = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(reuqest);
	}
}
