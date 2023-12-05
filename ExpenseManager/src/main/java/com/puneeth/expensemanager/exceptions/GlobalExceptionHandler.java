package com.puneeth.expensemanager.exceptions;



import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionData> resourceNotFoundHandler(ResourceNotFoundException ex){
		ExceptionData err= new ExceptionData();
		err.setStatusCode(HttpStatus.NOT_FOUND.value());
		err.setTimestamp(new Date());
		err.setMessage(ex.getMessage());
		return new ResponseEntity<ExceptionData>(err, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ExceptionData> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex){
		ExceptionData err= new ExceptionData();
		err.setMessage(ex.getMessage());
		err.setStatusCode(HttpStatus.BAD_REQUEST.value());
		err.setTimestamp(new Date());
		return new ResponseEntity<ExceptionData>(err,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionData> exceptionHandler(Exception ex){
		ExceptionData err= new ExceptionData();
		err.setMessage(ex.getMessage());
		err.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		err.setTimestamp(new Date());
		return new ResponseEntity<ExceptionData>(err,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(ItemAlreadyExistsException.class)
	public ResponseEntity<ExceptionData> itemAlreadyExistsExceptionHandler(ItemAlreadyExistsException ex){
		ExceptionData err= new ExceptionData();
		err.setMessage(ex.getMessage());
		err.setStatusCode(HttpStatus.CONFLICT.value());
		err.setTimestamp(new Date());
		return new ResponseEntity<ExceptionData>(err,HttpStatus.CONFLICT);
	}
}
