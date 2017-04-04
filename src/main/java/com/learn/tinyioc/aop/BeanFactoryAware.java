package com.learn.tinyioc.aop;

import com.learn.tinyioc.beans.factory.BeanFactory;

public interface BeanFactoryAware {
	void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
