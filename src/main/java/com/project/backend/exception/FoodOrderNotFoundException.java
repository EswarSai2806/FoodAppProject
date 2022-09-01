package com.project.backend.exception;

public class FoodOrderNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	int id;
	String message = "Food Order Id Not Found";

	@Override
	public String getMessage() {
		return message;
	}

	public FoodOrderNotFoundException(int id) {
		super();
		this.id = id;
	}
	
}
