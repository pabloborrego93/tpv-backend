package com.pbg.tpvbackend.architecture.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pbg.tpvbackend.dto.ErrorMSGApiDto;
import com.pbg.tpvbackend.dto.ExceptionMappingDto;
import com.pbg.tpvbackend.dto.FieldErrorMessage;
import com.pbg.tpvbackend.utils.StringUtils;

public abstract class BaseExceptionHandler {
	
	private static final ExceptionMappingDto DEFAULT_ERROR = ExceptionMappingDto
		.builder()
		.status(HttpStatus.INTERNAL_SERVER_ERROR)
		.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
		.message("Internal server error")
		.build();

	private final Map<Class<?>, ExceptionMappingDto> exceptionMappingDtos = new HashMap<>();

	public BaseExceptionHandler() {}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<?> handleThrowable(final Throwable ex, final HttpServletResponse response) {
		
		/* If the excepcion occurs while validating dto's 
		 * in controllers we call the function to handle this 
		 */
		if(ex instanceof MethodArgumentNotValidException) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(handleFieldValidationFailFromController(ex));
		}
		
		/* If the excepcion occurs while validating dto's
		 * in services we call the function to handle this 
		 */
		if(ex instanceof ConstraintViolationException) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(handleFieldValidationFailFromService(ex));
		}
		
		ExceptionMappingDto mapping = exceptionMappingDtos.getOrDefault(ex.getClass(), DEFAULT_ERROR);
		response.setStatus(mapping.getStatus().value());
		return ResponseEntity.status(mapping.getStatus()).body(new ErrorMSGApiDto(mapping.getCode(), mapping.getMessage()));
	}
	
	private List<FieldErrorMessage> handleFieldValidationFailFromController(final Throwable ex) {
		 return ((MethodArgumentNotValidException) ex)
			.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(fe -> new FieldErrorMessage(fe))
			.collect(Collectors.toList());
	}
	
	private List<FieldErrorMessage> handleFieldValidationFailFromService(final Throwable ex) {
		 return ((ConstraintViolationException) ex)
			.getConstraintViolations()
			.stream()
			.map(fe -> new FieldErrorMessage(
				StringUtils.getLastDotSplit(fe.getPropertyPath().toString()), 
				fe.getMessageTemplate()))
			.collect(Collectors.toList());
	}

	protected void registerMapping(final Class<?> clazz, final HttpStatus status, final String message) {
		exceptionMappingDtos.put(clazz, new ExceptionMappingDto(status, status.value(), message));
	}
	
	protected void registerMapping(final List<Class<?>> clazz, final HttpStatus status, final String message) {
		clazz.forEach(c -> {
			registerMapping(c, status, message);
		});
	}

}
