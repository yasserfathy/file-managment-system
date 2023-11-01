package com.stc.filesSystem.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils<T> {
	
	private Class<T> entityClass;

	public JsonUtils(Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
	}
	
	public T convertString_To_JSONObject(String s) throws Exception, JacksonException {
		 ObjectMapper mapper = new ObjectMapper();
		 T respPojo = mapper.readValue(s,entityClass);
		 return respPojo;
	}
	
	public String convertJSONObject_To_String(T object) throws Exception, JacksonException {
		 ObjectMapper mapper = new ObjectMapper();
		 String respPojo = mapper.writeValueAsString(object);
		 return respPojo;
	}

}
