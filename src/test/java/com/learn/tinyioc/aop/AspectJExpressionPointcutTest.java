package com.learn.tinyioc.aop;

import org.junit.Assert;
import org.junit.Test;

import com.learn.tinyioc.HelloWorldService;
import com.learn.tinyioc.HelloWorldServiceImpl;
import com.learn.tinyioc.beans.factory.HelloWorld;
import com.learn.tinyioc.beans.factory.HelloWorldInterface;



public class AspectJExpressionPointcutTest {

	@Test
	public void testClassFilterMatches() {
		String expression="execution(* com.learn.tinyioc..*.*(..))";
		AspectJExpressionPointcut aspectJExpressionPointcut=new AspectJExpressionPointcut();
		aspectJExpressionPointcut.setExpression(expression);
		boolean matches=aspectJExpressionPointcut.getClassFilter().matches(HelloWorldInterface.class);
		Assert.assertTrue(matches);
		
	}

	@Test
	public void testMethodMatcherMatches() throws Exception {
		String expression="execution(* com.learn.tinyioc..*.*(..))";
		AspectJExpressionPointcut aspectJExpressionPointcut=new AspectJExpressionPointcut();
		aspectJExpressionPointcut.setExpression(expression);
		//System.out.print(HelloWorld.class.getDeclaredMethod("helloWorld"));
		boolean matches=aspectJExpressionPointcut.getMethodMatcher().matches(HelloWorld.class.getDeclaredMethod("helloWorld"),HelloWorld.class);
		Assert.assertTrue(matches);
	}

}
