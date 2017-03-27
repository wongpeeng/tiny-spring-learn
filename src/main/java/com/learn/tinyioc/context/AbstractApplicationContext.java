package com.learn.tinyioc.context;

import com.learn.tinyioc.beans.factory.AbstractBeanFactory;

public class AbstractApplicationContext implements ApplicationContext {
	protected AbstractBeanFactory beanFactory;

	public AbstractApplicationContext(AbstractBeanFactory beanFactory){
		this.beanFactory=beanFactory;
	}
	
	public void refresh() throws Exception{
		
	}
	
	public Object getBean(String beanName) throws Exception{
		return beanFactory.getBean(beanName);
	}
	
}
