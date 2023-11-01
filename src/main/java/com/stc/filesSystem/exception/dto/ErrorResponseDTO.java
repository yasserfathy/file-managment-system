package com.stc.filesSystem.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder

public class ErrorResponseDTO implements Serializable {
	
    private List<CustomErrorDTO> errors;

	public ErrorResponseDTO(List<CustomErrorDTO> errors) {
		super();
		this.errors = errors;
	}

	public ErrorResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<CustomErrorDTO> getErrors() {
		return errors;
	}

	public void setErrors(List<CustomErrorDTO> errors) {
		this.errors = errors;
	}
    
    
}


