package com.adrian.work.aredis.exception;

public class AredisResponseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AredisResponseException(String message) {
		super(message);
	}

}
