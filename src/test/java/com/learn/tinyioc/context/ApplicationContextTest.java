package com.learn.tinyioc.context;

import static org.junit.Assert.*;

import org.junit.Test;

import com.learn.tinyioc.beans.factory.HelloWorld;

public class ApplicationContextTest {

	@Test
	public void test() throws Exception {
		ApplicationContext cxt=new ClassPathXmlApplicationContext("tinyioc.xml");
		HelloWorld helloWorld=(HelloWorld)cxt.getBean("helloWorld");
		helloWorld.helloWorld();
		
	}

}
