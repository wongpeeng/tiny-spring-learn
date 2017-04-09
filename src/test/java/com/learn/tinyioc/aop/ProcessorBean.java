package com.learn.tinyioc.aop;

import com.learn.tinyioc.beans.BeanPostProcessor;

public class ProcessorBean implements BeanPostProcessor {

	@Override
	public Object postProcessorBeforeInitialization(Object bean, String beanName) throws Exception {
		System.out.print("bean processor! start initialzing!\n");
		return bean;
	}

	@Override
	public Object postProcessorAfterInitialization(Object bean, String beanName) throws Exception {
		System.out.print("bean processor! end initialzing!\n");
		return bean;
	}

}
