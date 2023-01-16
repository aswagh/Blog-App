package com.project.blogApp.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.blogApp.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

		private ApiResponse apiResponse;
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
			String msg = ex.getMessage();
			apiResponse = new ApiResponse(msg, false);
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		}
		
//		@ExceptionHandler(MethodArgumentNotValidException.class)
//		public ResponseEntity<ApiResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex){
//			String msg = ex.getMessage();
//			apiResponse = new ApiResponse(msg,false);
//			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
//		}

		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
			Map<String, String> resp = new HashMap<>();
			
			ex.getBindingResult().getAllErrors().forEach((error)-> {
				String fieldName = ((FieldError)error).getField();
				String message = error.getDefaultMessage();
				resp.put(fieldName, message);
			});
			return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		}

}




