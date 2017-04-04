package com.learn.tinyioc.aop;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.learn.tinyioc.beans.factory.BeanFactory;

public class AspectJAroundAdvice implements Advice, MethodInterceptor {

	private BeanFactory beanFactory;
	private	Method aspectJAdviceMethod;
	private String	aspectJInstanceName;
	public BeanFactory getBeanFactory() {
		return beanFactory;
	}
	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	public Method getAspectJAdviceMethod() {
		return aspectJAdviceMethod;
	}
	public void setAspectJAdviceMethod(Method aspectJAdviceMethod) {
		this.aspectJAdviceMethod = aspectJAdviceMethod;
	}
	public String getAspectJInstanceName() {
		return aspectJInstanceName;
	}
	public void setAspectJInstanceName(String aspectJInstanceName) {
		this.aspectJInstanceName = aspectJInstanceName;
	}
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		return aspectJAdviceMethod.invoke(beanFactory.getBean("aspectJInstanceName"), invocation);
	}

}
