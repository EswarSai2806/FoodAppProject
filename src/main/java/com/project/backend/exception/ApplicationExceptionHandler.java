package com.project.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.backend.util.ResponseStructure;

public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> idNotFoundException(ApplicationExceptionHandler exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Id Not Found In Database");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setT("No Such Id Found");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

}
