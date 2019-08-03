package org.smart4j.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * «Î«Û∂‘œÛ
 * @author 79959
 *
 */
public class Request {
	private String reuqestMethod;
	private String requestPath;
	
	public Request(String requestmethod , String reuqestPath) {
		this.reuqestMethod = requestmethod;
		this.requestPath = reuqestPath;
	}

	public String getReuqestMethod() {
		return reuqestMethod;
	}

	public String getRequestPath() {
		return requestPath;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this,obj);
	}
	
	
	
}
