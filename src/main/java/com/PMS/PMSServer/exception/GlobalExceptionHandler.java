package com.PMS.PMSServer.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.PMS.PMSServer.exception.authentication.AuthException;
import com.PMS.PMSServer.exception.parcel.ParcelException;
import com.PMS.PMSServer.exception.user.UserException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		
		logger.error("\n"+e.toString());
		ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.toString(),
				System.currentTimeMillis());
		return ResponseEntity.internalServerError().body(response);

	}
	
	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException e) {
		ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.badRequest().body(response);

	}
	
	
	@ExceptionHandler({ UserException.class })
	public ResponseEntity<ErrorResponse> handleUserException(Exception e) {
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(response,HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler({ AuthException.class })
	public ResponseEntity<ErrorResponse> handleAuthException(Exception e) {
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(response,HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler({ ParcelException.class })
	public ResponseEntity<ErrorResponse> handleParcelException(Exception e) {
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(response,HttpStatus.NOT_FOUND);

	}


}
