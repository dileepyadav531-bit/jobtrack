package com.jobtrack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jobtrack.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(EmailAlreadyExitException.class)
	public ResponseEntity<ErrorResponse> handleEmailAlreadyExit(EmailAlreadyExitException ex){
		
		
		
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(ex.getMessage()));
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIlligalArgumentException(IllegalArgumentException ex){
		
		ErrorResponse errorResponse = new ErrorResponse("Invalid application status");
		
		return new ResponseEntity<>(
				errorResponse
				,HttpStatus.BAD_REQUEST
				);
		
	}
	
	@ExceptionHandler(DuplicateApplicationException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateApplication(
			DuplicateApplicationException ex){
		
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
		
		return new ResponseEntity<>(
				errorResponse,HttpStatus.CONFLICT
				);
		
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleInvalidCredentials(
	        InvalidCredentialsException ex) {

	    ErrorResponse errorResponse =
	            new ErrorResponse(ex.getMessage());

	    return new ResponseEntity<>(
	            errorResponse,
	            HttpStatus.UNAUTHORIZED
	    );
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(
	        MethodArgumentNotValidException ex) {

	    ErrorResponse errorResponse =
	            new ErrorResponse("Please enter valid registration details");

	    return new ResponseEntity<>(
	            errorResponse,
	            HttpStatus.BAD_REQUEST
	    );
	}
	
	@ExceptionHandler(JobNotAvailableException.class)
	public ResponseEntity<ErrorResponse> handleJobNotAvailable(JobNotAvailableException ex){
		
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
		
		return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
	}

}
