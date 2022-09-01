package com.project.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.project.backend.util.ResponseStructure;

public class FoodOrderExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(FoodOrderNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> foodOrderNotFoundException(FoodOrderNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Food Order With "+exception.id +"Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setT("Food Order Id not Found for the given input id");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
