package com.learn.tinyioc.beans;

import java.util.HashMap;
import java.util.Map;

import com.learn.tinyioc.beans.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
	private Map<String,BeanDefinition> registry;
	private ResourceLoader	resourceLoader;
	
	public AbstractBeanDefinitionReader(ResourceLoader resourceLoader){
		this.resourceLoader=resourceLoader;
		registry=new HashMap<String,BeanDefinition>();
	}
	public Map<String,BeanDefinition> getRegistry(){
		return registry;
	}
	public ResourceLoader getResouceLoader(){
		return resourceLoader;
	}
}
