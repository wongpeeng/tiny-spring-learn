package com.learn.tinyioc.beans.factory;

import org.junit.Assert;

public class SayHello {
	private HelloWorld helloWorld;
	public void hello(String content){
		Assert.assertNotNull(helloWorld);
		System.out.println(content);
	}
}
