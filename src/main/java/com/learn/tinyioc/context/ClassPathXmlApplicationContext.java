package com.learn.tinyioc.context;

import java.util.Map;

import com.learn.tinyioc.beans.BeanDefinition;
import com.learn.tinyioc.beans.factory.AbstractBeanFactory;
import com.learn.tinyioc.beans.factory.AutowireCapableBeanFactory;
import com.learn.tinyioc.beans.io.ResourceLoader;
import com.learn.tinyioc.beans.xml.XmlBeanDefinitionReader;


public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
	private String location;
	public ClassPathXmlApplicationContext(String location) throws Exception{
		this(location,new AutowireCapableBeanFactory());
	}
	
	public ClassPathXmlApplicationContext(String location,AbstractBeanFactory beanFactory) throws Exception{
		super(beanFactory);
		this.location=location;
		refresh();
	}
	
	public void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception{
		XmlBeanDefinitionReader xmlReader=new XmlBeanDefinitionReader(new ResourceLoader());
		xmlReader.loadBeanDefinitions(location);
		for(Map.Entry<String, BeanDefinition> beanDefinition: xmlReader.getRegistry().entrySet()){
			beanFactory.registerBeanDefinition(beanDefinition.getKey(), beanDefinition.getValue());
		}
	}
	
}
