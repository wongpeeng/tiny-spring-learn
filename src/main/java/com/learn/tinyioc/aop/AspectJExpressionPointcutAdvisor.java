package com.learn.tinyioc.aop;

import org.aopalliance.aop.Advice;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

	private Advice advice;
	public Advice getAdvice() {
		return advice;
	}
	public void setPointcut(AspectJExpressionPointcut pointcut) {
		this.pointcut = pointcut;
	}

	private AspectJExpressionPointcut pointcut=new AspectJExpressionPointcut();
	public Pointcut getPointcut() {
		// TODO Auto-generated method stub
		return pointcut;
	}
	public void setAdvice(Advice advice){
		this.advice=advice;
	}
	
	public void setExpression(String expression){
		this.pointcut.setExpression(expression);
	}

}
