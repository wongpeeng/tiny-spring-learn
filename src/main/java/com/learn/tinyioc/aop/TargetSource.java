package com.learn.tinyioc.aop;

public class TargetSource {
	private Class<?>[] 	targetClass;
	private Object	target;
	public TargetSource(Object target, Class<?>... targetClass){
		this.target=target;
		this.targetClass=targetClass;
	}
	public Class<?>[] getTargetClass() {
		return targetClass;
	}
	public void setTargetClass(Class<?>[] targetClass) {
		this.targetClass = targetClass;
	}
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}
	
}
