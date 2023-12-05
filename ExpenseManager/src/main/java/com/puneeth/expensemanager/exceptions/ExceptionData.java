package com.puneeth.expensemanager.exceptions;



import java.util.Date;

import lombok.Data;

@Data
public class ExceptionData {

	private Integer statusCode;
	private String message;
	private Date timestamp;
	
}
