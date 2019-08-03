package org.smart4j.framework.bean;

import java.lang.reflect.Method;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Handler {
	private Class<?> controllerClass;
	
	private Method actionMethod;
	
	public Handler(Class<?> controllerClass,Method anctionMethod) {
		this.controllerClass = controllerClass;
		this.actionMethod = anctionMethod;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public Method getActionMethod() {
		return actionMethod;
	}

	
	
}
