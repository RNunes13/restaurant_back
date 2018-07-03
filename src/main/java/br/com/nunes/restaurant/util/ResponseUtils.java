package br.com.nunes.restaurant.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;

public class ResponseUtils {
	
	public static ResponseEntity<CustomListResponse> invalid(List<ObjectError> errors) {
		List<String> messages = new ArrayList<String>();
		if(errors != null) {
			for (ObjectError error : errors) {
				messages.add(error.getDefaultMessage());
			}		
		}
		return new ResponseEntity<CustomListResponse>(new CustomListResponse(messages, false), HttpStatus.BAD_REQUEST);
		
	}
	
	public static ResponseEntity<CustomResponse> invalid(String message) {
		return new ResponseEntity<CustomResponse>(new CustomResponse(message, false), HttpStatus.BAD_REQUEST);
		
	}
	
	public static ResponseEntity<CustomResponse> itemNotFound(String message) {
		return new ResponseEntity<CustomResponse>(new CustomResponse(message, false), HttpStatus.FAILED_DEPENDENCY);
	}
	
	public static ResponseEntity<CustomResponse> failed(String message) {
		return new ResponseEntity<CustomResponse>(new CustomResponse(message, false), HttpStatus.EXPECTATION_FAILED);
	}
	
	public static ResponseEntity<CustomResponse> ok(String message) {
		return new ResponseEntity<CustomResponse>(new CustomResponse(message, true), HttpStatus.OK);
	}
	
	public static ResponseEntity<? extends Object> ok(Object obj) {
		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}
	
	public static ResponseEntity<? extends Object> ok(Serializable obj) {
		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}
	
	public static ResponseEntity<? extends Object> rawOk(Object obj) {
		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}
	
	public static ResponseEntity<CustomResponse> response(String message, boolean success, HttpStatus status) {
		return new ResponseEntity<CustomResponse>(new CustomResponse(message, success), status);
	}
	

}
