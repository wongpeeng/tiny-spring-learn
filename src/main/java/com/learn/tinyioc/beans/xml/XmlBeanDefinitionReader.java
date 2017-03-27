package com.learn.tinyioc.beans.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.learn.tinyioc.beans.AbstractBeanDefinitionReader;
import com.learn.tinyioc.beans.BeanDefinition;
import com.learn.tinyioc.beans.BeanReference;
import com.learn.tinyioc.beans.PropertyValue;
import com.learn.tinyioc.beans.io.Resource;
import com.learn.tinyioc.beans.io.ResourceLoader;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{
	public XmlBeanDefinitionReader(ResourceLoader resourceLoader){
		super(resourceLoader);
	}
	public void loadBeanDefinitions(String location) throws Exception{
		Resource resource=this.getResouceLoader().getResource(location);
		InputStream inputStream=resource.getInputStream();
		doLoadBeanDefinitions(inputStream);
	}
	
	protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document doc=builder.parse(inputStream);
		registerBeanDefinitions(doc);
		inputStream.close();
	}
	
	protected void registerBeanDefinitions(Document doc){
		Element root=doc.getDocumentElement(); 
		parseBeanDefinitions(root);
	}
	
	protected void parseBeanDefinitions(Element root){
		NodeList nodeList=root.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++){
			Node node=nodeList.item(i);
			if(node instanceof Element){
				Element element=(Element) node;
				processBeanDefinitions(element);
			}
		}
	}
	
	protected void processBeanDefinitions(Element element){
		String name=element.getAttribute("name");
		String className=element.getAttribute("class");
		BeanDefinition beanDefinition=new BeanDefinition();
		processProperties(element,beanDefinition);
		beanDefinition.setBeanClassName(className);
		this.getRegistry().put(name, beanDefinition);
	}
	
	protected void processProperties(Element element, BeanDefinition beanDefinition){
		NodeList nodeList=element.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++){
			Node node=nodeList.item(i);
			if(node instanceof Element){
				Element propertyElement=(Element)node;
				String name=propertyElement.getAttribute("name");
				String value=propertyElement.getAttribute("value");
				if(value!= null && value.length()>0){
					beanDefinition.getBeanPropertyValues().addPropertyValue(new PropertyValue(name,value));
				}else{
					String ref=propertyElement.getAttribute("ref");
					if(ref == null || ref.length()==0){
						throw new IllegalArgumentException("Configuration problem: <property> element for property '"
								+ name + "' must specify a ref or value");
					}else{
						BeanReference beanRefrerence =new BeanReference(name);
						beanDefinition.getBeanPropertyValues().addPropertyValue(new PropertyValue(name,beanRefrerence));
						}
				}
				
			}
		}
	}
}
