package com.learn.tinyioc.beans;

public class BeanDefinition {
	private String 	beanClassName;
	private Class	beanClass;
	private Object	bean;
	private PropertyValues beanPropertyValues=new PropertyValues();
	public String getBeanClassName() {
		return beanClassName;
	}
	public void setBeanClassName(String beanClassName)  {
		this.beanClassName = beanClassName;
		try{
		this.beanClass=Class.forName(beanClassName);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public Class getBeanClass() {
		return beanClass;
	}
	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}
	public Object getBean() {
		return bean;
	}
	public void setBean(Object bean) {
		this.bean = bean;
	}
	public PropertyValues getBeanPropertyValues() {
		return beanPropertyValues;
	}
	public void setBeanPropertyValues(PropertyValues beanPropertyValues) {
		this.beanPropertyValues = beanPropertyValues;
	}
	
}
