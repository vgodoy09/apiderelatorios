package com.novotempo.endofyeartoast.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.novotempo.endofyeartoast.config.ResourceException.ResponseCode;

public class NTResponseCode<T extends MessageCodeable> extends ResponseEntity<T> {
	public NTResponseCode(T responseObject, ResponseCode responseCode, String message) {
		super(responseObject, HttpStatus.valueOf(responseCode.getHttpStatus()));
		responseObject.setCode(responseCode.getInternalCode());
		responseObject.setMessage(message);
	}
}