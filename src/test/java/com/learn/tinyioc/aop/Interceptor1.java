package com.learn.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Interceptor1 implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("Interceptor 1 starts....");
		Object proceed=invocation.proceed();
		System.out.println("Interceptor 1 ends....");
		return proceed;
	}

}
