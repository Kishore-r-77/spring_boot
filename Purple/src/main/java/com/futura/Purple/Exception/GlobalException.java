package com.futura.Purple.Exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.futura.Purple.dto.ErrorDetails;

@ControllerAdvice
public class GlobalException {
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ErrorDetails> handleException(Exception exception,WebRequest request){
//		ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
//		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,WebRequest request){
		Map<String, String> errors = new HashMap<>();
		
		exception.getBindingResult().getAllErrors().forEach((error)->{
			String fieldname = ((FieldError)error).getField();
			String message=error.getDefaultMessage();
			errors.put(fieldname, message);
		});
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}

}
