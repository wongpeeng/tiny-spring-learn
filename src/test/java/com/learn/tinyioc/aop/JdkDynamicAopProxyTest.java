package com.learn.tinyioc.aop;

import static org.junit.Assert.*;

import org.junit.Test;

import com.learn.tinyioc.beans.factory.HelloWorld;
import com.learn.tinyioc.beans.factory.HelloWorldInterface;
import com.learn.tinyioc.context.ApplicationContext;
import com.learn.tinyioc.context.ClassPathXmlApplicationContext;

public class JdkDynamicAopProxyTest {

	@Test
	public void test() throws Exception{
		ApplicationContext cxt=new ClassPathXmlApplicationContext("tinyioc.xml");
		HelloWorld hw=(HelloWorld)cxt.getBean("helloWorld");
		AdvisedSupport advice=new AdvisedSupport();
		TargetSource target=new TargetSource(hw,hw.getClass());
		advice.setTargetSource(target);
		Interceptor1 interceptor1=new Interceptor1();
		advice.setMethodInterceptor(interceptor1);
		
		JdkDynamicAopProxy jProxy=new JdkDynamicAopProxy(advice);
		HelloWorldInterface hwi=(HelloWorldInterface)jProxy.getProxy();
		hwi.helloWorld();
		
		
	}

}
