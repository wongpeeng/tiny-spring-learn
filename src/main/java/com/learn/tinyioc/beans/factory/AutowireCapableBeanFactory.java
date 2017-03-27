package com.learn.tinyioc.beans.factory;

import java.lang.reflect.Field;
import java.util.List;

import com.learn.tinyioc.beans.BeanDefinition;
import com.learn.tinyioc.beans.BeanReference;
import com.learn.tinyioc.beans.PropertyValue;
public class AutowireCapableBeanFactory  extends AbstractBeanFactory{
	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception{
		Object bean=CreateBeanInstance(beanDefinition);
		beanDefinition.setBean(bean);
		applyPropertyVaules(bean,beanDefinition);
		return bean;
	}
	protected Object CreateBeanInstance(BeanDefinition beanDefinition) throws Exception{
		Class beanClass=beanDefinition.getBeanClass();
		return beanClass.newInstance();
	}
	protected void applyPropertyVaules(Object bean,BeanDefinition beanDefinition) throws Exception{
		List<PropertyValue> pList=beanDefinition.getBeanPropertyValues().getPropertyValueList();
		for(PropertyValue p:pList){
			Field declaredField =bean.getClass().getDeclaredField(p.getName());
			declaredField.setAccessible(true);
			Object value=p.getValue();
			if(value instanceof BeanReference){
				BeanReference beanReference =(BeanReference) value;
				value=this.getBean(beanReference.getName());
			}
			declaredField.set(bean,value);
		}
		return;
	}

}
