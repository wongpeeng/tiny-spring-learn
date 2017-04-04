package com.learn.tinyioc.beans.factory;

public class HelloWorld implements HelloWorldInterface{
	private SayHello	sayHello;
	private String		content;
	public SayHello getSayHello() {
		return sayHello;
	}
	public void setSayHello(SayHello sayHello) {
		this.sayHello = sayHello;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public void helloWorld(){
		sayHello.hello(content);
	}
}
