package com.learn.tinyioc.beans;

public interface BeanPostProcessor {
	Object postProcessorBeforeInitialization(Object bean,String beanName) throws Exception;
	Object postProcessorAfterInitialization(Object bean,String beanName) throws Exception;
}
