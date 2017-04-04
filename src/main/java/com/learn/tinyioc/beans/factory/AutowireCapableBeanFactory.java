package com.learn.tinyioc.beans.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.learn.tinyioc.aop.BeanFactoryAware;
import com.learn.tinyioc.beans.BeanDefinition;
import com.learn.tinyioc.beans.BeanReference;
import com.learn.tinyioc.beans.PropertyValue;
public class AutowireCapableBeanFactory  extends AbstractBeanFactory{
	
	protected void applyPropertyVaules(Object bean,BeanDefinition beanDefinition) throws Exception{
		if(bean instanceof BeanFactoryAware){
			((BeanFactoryAware)bean).setBeanFactory(this);
		}

		List<PropertyValue> pList=beanDefinition.getBeanPropertyValues().getPropertyValueList();
		for(PropertyValue p:pList){
			Object value=p.getValue();
			if(value instanceof BeanReference){
				BeanReference beanReference =(BeanReference) value;
				value=this.getBean(beanReference.getName());
			}
			try{
				Method declaredMethod=bean.getClass().getDeclaredMethod("set"+p.getName().substring(0, 1)+p.getName().substring(1));
				declaredMethod.setAccessible(true);
				declaredMethod.invoke(bean, value);
			}catch(NoSuchMethodException e){
				Field declaredField =bean.getClass().getDeclaredField(p.getName());
				declaredField.setAccessible(true);
				declaredField.set(bean,value);
			}

		}
		return;
	}

}
