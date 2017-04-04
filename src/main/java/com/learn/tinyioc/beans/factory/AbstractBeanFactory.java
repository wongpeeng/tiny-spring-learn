package com.learn.tinyioc.beans.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.learn.tinyioc.beans.BeanDefinition;
import com.learn.tinyioc.beans.BeanPostProcessor;
public abstract class AbstractBeanFactory implements BeanFactory {
	private Map<String,BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<String,BeanDefinition>();
	private final List<String> beanDefinitionNames=new ArrayList<String>();
	private List<BeanPostProcessor> beanPostProcessors=new ArrayList<BeanPostProcessor>();
	
	
	public Object getBean(String beanName) throws Exception{
		BeanDefinition beanDefinition =beanDefinitionMap.get(beanName);
		if(beanDefinition == null){
			throw new IllegalArgumentException("bean "+beanName+" is not defined in confifuration!");
		}
		Object bean=beanDefinition.getBean();
		if(bean ==null){
			bean=doCreateBean(beanDefinition);
			bean=initializeBean(bean,beanName);
		}
		return bean;
	}
	public Object initializeBean(Object bean,String name)throws Exception{
		for(BeanPostProcessor beanPostProcessor:beanPostProcessors)
			bean=beanPostProcessor.postProcessorBeforeInitialization(bean, name);
		
		for(BeanPostProcessor beanPostProcessor:beanPostProcessors)
			bean=beanPostProcessor.postProcessorAfterInitialization(bean,name);
		
		return bean;
	}
	
	public void registerBeanDefinition(String name,BeanDefinition beanDefinition){
		beanDefinitionMap.put(name, beanDefinition);
		beanDefinitionNames.add(name);
		
	}
	
	public void preInstantiateSingletons() throws Exception{
		Iterator it=beanDefinitionNames.iterator();
		while(it.hasNext()){
			String name=(String)it.next();
			this.getBean(name);
		}
	}
	
	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception{
		Object bean=createBeanInstance(beanDefinition);
		beanDefinition.setBean(bean);
		applyPropertyValues(bean,beanDefinition);
		return bean;
	}
	
	protected void applyPropertyValues(Object bean,BeanDefinition beanDefinition) throws Exception{
		
	}

	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
		beanPostProcessors.add(beanPostProcessor);
	}
	
	protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception{
		Class beanClass=beanDefinition.getBeanClass();
		return beanClass.newInstance();
	}
	
	public List getBeansForType(Class type) throws Exception{
		List beans =new ArrayList<Object>();
		for(String beanDefinitionName:beanDefinitionNames){
			if(type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
				beans.add(getBean(beanDefinitionName));
			}
		}
		return beans;
	}
}
