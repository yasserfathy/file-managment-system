package com.stc.filesSystem.exception.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CustomErrorDTO extends RuntimeException implements Serializable {

    private String message;
    private Integer errorCode;
	public CustomErrorDTO(String message, Integer errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
    
    
}