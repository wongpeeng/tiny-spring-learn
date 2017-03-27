package com.learn.tinyioc.beans.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.learn.tinyioc.beans.BeanDefinition;
public abstract class AbstractBeanFactory implements BeanFactory {
	private Map<String,BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<String,BeanDefinition>();
	private final List<String> beanDefinitionNames=new ArrayList<String>();
	
	public Object getBean(String beanName) throws Exception{
		BeanDefinition beanDefinition =beanDefinitionMap.get(beanName);
		if(beanDefinition == null){
			throw new IllegalArgumentException("bean"+beanName+"is not defined in confifuration!");
		}
		Object bean=beanDefinition.getBean();
		if(bean ==null){
			bean=doCreateBean(beanDefinition);
			
		}
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
	
	protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
	}
