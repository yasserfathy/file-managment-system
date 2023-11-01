package com.stc.filesSystem.exception.handler;

import com.stc.filesSystem.enums.ErrorCode;
import com.stc.filesSystem.exception.ObjectNotFoundException;
import com.stc.filesSystem.exception.dto.CustomErrorDTO;
import com.stc.filesSystem.exception.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.util.ArrayList;

import static com.stc.filesSystem.enums.ErrorCode.MAX_UPLOAD_SIZE_EXCEEDED;

@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

	private String multipartMaxFileSize = "14M";

	public ArrayList<CustomErrorDTO> errorList;
	public CustomErrorDTO customErrorDTO;
	public ErrorResponseDTO errorResponseDTO;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception e) {
		// log.error("Error in controller layer: [{}] ", e);

		System.out.println("Error in controller layer: [{}] " + e);

		errorList = new ArrayList<>();

		customErrorDTO = new CustomErrorDTO(ErrorCode.INTERNAL_SERVER_ERROR.name(),
				ErrorCode.INTERNAL_SERVER_ERROR.getValue());

		errorList.add(customErrorDTO);


		errorResponseDTO = new ErrorResponseDTO(errorList);

		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
		System.out.println("Error in controller layer: [{}] "+ e);

		errorList = new ArrayList<>();

		customErrorDTO = new CustomErrorDTO(ErrorCode.INTERNAL_SERVER_ERROR.name(),
				ErrorCode.INTERNAL_SERVER_ERROR.getValue());

		errorList.add(customErrorDTO);

//        errorList.add(CustomErrorDTO.builder()
//                .message(ErrorCode.INTERNAL_SERVER_ERROR.name())
//                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR.getValue())
//                .build());
		errorResponseDTO = new ErrorResponseDTO(errorList);

		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<CustomErrorDTO> handleMaxSizeException(MaxUploadSizeExceededException exc,
			HttpServletRequest request, HttpServletResponse response) {

		Throwable rootCause = exc.getRootCause();
		System.out.println("MaxUploadSizeExceededException"+ rootCause.getMessage());

		customErrorDTO = new CustomErrorDTO(
				MAX_UPLOAD_SIZE_EXCEEDED.getMessage() + ", Maximum size is " + multipartMaxFileSize,
				MAX_UPLOAD_SIZE_EXCEEDED.getValue());

//        CustomErrorDTO.builder()
//        .message(MAX_UPLOAD_SIZE_EXCEEDED.getMessage() + ", Maximum size is " + multipartMaxFileSize)
//        .errorCode(MAX_UPLOAD_SIZE_EXCEEDED.getValue())
//        .build()
		return new ResponseEntity<>(customErrorDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<CustomErrorDTO> handleObjectNotFoundException(ObjectNotFoundException exc) {

		/** 1: create errorResponse */
		System.out.println("ObjectNotFoundException exception {}"+ exc.getMessage());
		customErrorDTO = new CustomErrorDTO(exc.getMessage(),
				ErrorCode.NOT_FOUND.getValue());

		/** return errorResponse */
		return new ResponseEntity<>(customErrorDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ javax.validation.ConstraintViolationException.class })
	public ResponseEntity<?> handleValidationExceptionException(javax.validation.ConstraintViolationException e) {
		System.out.println("Error in controller layer: [{}] "+ e);
	     errorList = new ArrayList<>();

		for (ConstraintViolation violation : e.getConstraintViolations()) {
			customErrorDTO = new CustomErrorDTO(ErrorCode.DATA_NOT_VALID.name(),
					ErrorCode.DATA_NOT_VALID.getValue());
			errorList.add(customErrorDTO);
		}
		
		errorResponseDTO = new ErrorResponseDTO(errorList);
		
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}

}
