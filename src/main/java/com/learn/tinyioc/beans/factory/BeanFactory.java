package com.learn.tinyioc.beans.factory;

public interface BeanFactory {
	Object getBean(String beanName) throws Exception;
}
