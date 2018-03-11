package com.pbg.tpvbackend.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pbg.tpvbackend.dto.ErrorMSGApiDto;
import com.pbg.tpvbackend.dto.ExceptionMappingDto;

public abstract class BaseExceptionHandler {
	
	private static final ExceptionMappingDto DEFAULT_ERROR = ExceptionMappingDto
		.builder()
		.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
		.message("Internal server error")
		.status(HttpStatus.INTERNAL_SERVER_ERROR)
		.build();

	private final Map<Class<?>, ExceptionMappingDto> exceptionMappingDtos = new HashMap<>();

	public BaseExceptionHandler() {}

	@ExceptionHandler(Throwable.class)
	public @ResponseBody ErrorMSGApiDto handleThrowable(final Throwable ex, final HttpServletResponse response) {
		ExceptionMappingDto mapping = exceptionMappingDtos.getOrDefault(ex.getClass(), DEFAULT_ERROR);
		response.setStatus(mapping.getStatus().value());
		return new ErrorMSGApiDto(mapping.getCode(), mapping.getMessage());
	}

	protected void registerMapping(final Class<?> clazz, final Integer code, final String message, final HttpStatus status) {
		exceptionMappingDtos.put(clazz, new ExceptionMappingDto(code, message, status));
	}

}
