package com.learn.tinyioc.aop;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;

import com.learn.tinyioc.beans.BeanPostProcessor;
import com.learn.tinyioc.beans.factory.AbstractBeanFactory;
import com.learn.tinyioc.beans.factory.BeanFactory;

public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {
	private AbstractBeanFactory beanFactory;
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws Exception {
		this.beanFactory=(AbstractBeanFactory)beanFactory;

	}

	@Override
	public Object postProcessorBeforeInitialization(Object bean, String beanName) throws Exception {
		// TODO Auto-generated method stub
		return bean;
	}

	@Override
	public Object postProcessorAfterInitialization(Object bean, String beanName) throws Exception {
		if(bean instanceof AspectJExpressionPointcutAdvisor){
			return bean;
		}
		if(bean instanceof MethodInterceptor){
			return bean;
		}
		List<AspectJExpressionPointcutAdvisor> advisors=beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
		for(AspectJExpressionPointcutAdvisor advisor :advisors){
			if(advisor.getPointcut().getClassFilter().matches(bean.getClass())){
				AdvisedSupport advisedSupport=new AdvisedSupport();
				advisedSupport.setMethodInterceptor((MethodInterceptor)advisor.getAdvice());
				advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
				
				TargetSource targetSource=new TargetSource(bean,bean.getClass().getInterfaces());
				advisedSupport.setTargetSource(targetSource);
				return new JdkDynamicAopProxy(advisedSupport).getProxy(); 
				
			}
		}
		return bean;
	}

}
