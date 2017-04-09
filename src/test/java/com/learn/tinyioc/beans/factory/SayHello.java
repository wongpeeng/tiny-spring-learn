package com.learn.tinyioc.beans.factory;

import org.junit.Assert;

public class SayHello implements SayHelloInterface{
	private HelloWorldInterface helloWorld;
	@Override
	public void hello(String content){
		Assert.assertNotNull(helloWorld);
		System.out.println(content);
	}
}
