package com.novotempo.endofyeartoast.config;

import org.springframework.http.HttpStatus;

public class ResourceException extends RuntimeException {
	private static final long serialVersionUID = -4052880149437789855L;

	public enum ResponseCode {
		/*200*/
		OK(200, 0), 
		CREATED(201, 0), 
		/*300*/
		/*400*/
		BAD_REQUEST(400, 0), 
		UNAUTHORIZED(401,0),
		NOT_FOUND(404, 0), 
		NOT_ACCEPTABLE(406, 0),
		/*400 address*/
		BAD_ADDRESS(406, 1),
		BAD_CEP(406, 2),
		BAD_CEP_FORMAT(406, 3),
		BAD_ADDRESS_STREET_TYPE(406, 4),
		BAD_ADDRESS_NUMBER(406, 5),
		BAD_ADDRESS_BLOCK(406, 6),
		BAD_ADDRESS_CITY(406, 7),
		BAD_ADDRESS_COUNTY(406, 8),
		BAD_ADDRESS_STATE(406, 9),
		BAD_ADDRESS_COUNTRY(406, 10),
		
		/*400 Initiative*/
		BAD_INITIATIVE(406,20),
		
		/*400 Product*/
		BAD_PRODUCT(406, 30),
		BAD_PRODUCT_UNAVAILABLE(406, 31),
		BAD_PRODUCT_REQUESTED_TIMES(406, 32),
		
		/*400 person*/
		BAD_PERSON(406, 50),
		BAD_PERSON_NAME(406, 51),
		BAD_PERSON_EMAIL(406, 52),
		BAD_PERSON_DOCUMENT(406, 53),
		BAD_PERSON_PHONE(406, 54),
		BAD_PERSON_BIRTHDATE(406, 55),
		BAD_PERSON_GENDER(406, 56),
		BAD_PERSON_IS_DEATH(406, 57),
		BAD_PERSON_COMMUNICATION_MEAN(406, 58),
		BAD_PERSON_MOTHER_NAME(406, 59),
		BAD_PERSON_MARITAL_STATUS(406, 60),
		BAD_PERSON_RELIGION(406, 61),
		BAD_PERSON_STUDENT(406, 62),
		
		/*500*/
		INTERNAL_SERVER_ERROR(500, 0), 
		NOT_IMPLEMENTED(501, 0), 
		CAN_NOT_SEND_EMAIL(500, 1), 
		CAN_NOT_CREATE_ISSUE(500, 2),
		CAN_NOT_CREATE_ATTENDANCE(500, 3),
		CAN_NOT_CLOSE_ATTENDANCE(500, 4),
		CAN_NOT_CREATE_SERVICE(500, 5),
		ATTENDANCE_CLOSED(500, 6),
		/*500 person*/
		CAN_NOT_CREATE_PERSON(500, 50),
		CAN_NOT_CREATE_USER(500, 51), 
		
		;private Integer httpStatus;
		private Integer internalCode;
		
		private ResponseCode(Integer httpStatus, Integer internalCode) {
			this.httpStatus = httpStatus;
			this.internalCode = internalCode;
		}
		
		public Integer getHttpStatus() {
			return this.httpStatus;
		}
		
		public Integer getInternalCode() {
			return this.internalCode;
		}
	}
	
	public Integer httpStatusValue;
	public Integer code;
	
	public ResourceException(ResponseCode responseCode, String message) {
		super(message);
		this.httpStatusValue = responseCode.getHttpStatus();
		this.code = responseCode.getInternalCode();
	}
	
	public ResourceException(HttpStatus httpStatus, Integer code, String message) {
		super(message);
		this.httpStatusValue = httpStatus.value();
		this.code = code;
	}

	public Integer getHttpStatusValue() {
		return httpStatusValue;
	}
	public Integer getCode() {
		return code;
	}
}
