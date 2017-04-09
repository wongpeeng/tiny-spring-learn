package com.learn.tinyioc.context;

import java.util.List;

import com.learn.tinyioc.beans.BeanPostProcessor;
import com.learn.tinyioc.beans.factory.AbstractBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext {
	protected AbstractBeanFactory beanFactory;

	public AbstractApplicationContext(AbstractBeanFactory beanFactory){
		this.beanFactory=beanFactory;
	}
	
	public void refresh() throws Exception{
		loadBeanDefinitions(beanFactory);
		registerBeanPostProcessor(beanFactory);
		onRefresh();
		
	}
	protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;
	
	protected void registerBeanPostProcessor(AbstractBeanFactory beanFactory) throws Exception{
		List beanPostProcessors=beanFactory.getBeansForType(BeanPostProcessor.class);
		for(Object beanPostProcessor:beanPostProcessors){
			beanFactory.addBeanPostProcessor((BeanPostProcessor)beanPostProcessor);
		}
	}
	
	
	protected void onRefresh() throws Exception{
		beanFactory.preInstantiateSingletons();
	}

	public Object getBean(String beanName) throws Exception{
		return beanFactory.getBean(beanName);
	}
	
}
