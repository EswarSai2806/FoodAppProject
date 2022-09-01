package com.project.backend.exception;

public class IdNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	String message = "Id not found";

	@Override
	public String getMessage() {
		return message;
	}
}
