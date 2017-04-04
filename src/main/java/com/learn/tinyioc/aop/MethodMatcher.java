package com.learn.tinyioc.aop;

import java.lang.reflect.Method;
//to judge the method to be intercepted
public interface MethodMatcher {
	boolean matches(Method method,Class targerClass);
}
