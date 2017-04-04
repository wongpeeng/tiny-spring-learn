package com.learn.tinyioc.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
	private AdvisedSupport advisedSupport;
	public JdkDynamicAopProxy(AdvisedSupport support){
		this.advisedSupport=support;
	}

	public Object invoke(final Object proxy,final  Method method,final Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		MethodInterceptor methodInterceptor =advisedSupport.getMethodInterceptor();
		if(advisedSupport.getMethodMatcher()!=null && 
				advisedSupport.getMethodMatcher().matches(method,advisedSupport.getTargetSource().getClass())){
			return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,args));
		}
		else
			return method.invoke(advisedSupport.getTargetSource().getTarget(),args);
	}

	public Object getProxy() {
		// TODO Auto-generated method stub
		return Proxy.newProxyInstance(this.getClass().getClassLoader(), this.advisedSupport.getTargetSource().getTargetClass(), this);
	}

}
