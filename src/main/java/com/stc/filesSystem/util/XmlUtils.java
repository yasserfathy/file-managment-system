package com.stc.filesSystem.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlUtils<T> {
	
	private Class<T> entityClass;

	public XmlUtils(Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
	}
	
	public T convertString_To_XMLObject(String s) throws Exception, JacksonException {
		 ObjectMapper mapper = new XmlMapper();
		 T respPojo = mapper.readValue(s,entityClass);
		 return respPojo;
	}
	
	public String convertXMLObject_To_String(T object) throws Exception, JacksonException {
		 ObjectMapper mapper = new XmlMapper();
		 String respPojo = mapper.writeValueAsString(object);
		 return respPojo;
	}

}
