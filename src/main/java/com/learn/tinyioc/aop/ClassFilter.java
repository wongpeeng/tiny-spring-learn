package com.learn.tinyioc.aop;


//to judge whether a class should be intercepted
public interface ClassFilter {
	boolean matches(Class targetClass);
}
