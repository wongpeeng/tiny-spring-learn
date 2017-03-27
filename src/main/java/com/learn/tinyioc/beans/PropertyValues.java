package com.learn.tinyioc.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
	private List<PropertyValue> propertyValueList=new ArrayList();
	public void addPropertyValue(PropertyValue propertyValue){
		propertyValueList.add(propertyValue);
	}
	public List<PropertyValue> getPropertyValueList(){
		return propertyValueList;
	}
	
}
