package com.adrian.work.aredis.exception;

public class AredisRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AredisRuntimeException(String message) {
		super(message);
	}
}
