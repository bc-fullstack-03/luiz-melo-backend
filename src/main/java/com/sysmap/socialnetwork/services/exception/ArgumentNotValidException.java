package com.sysmap.socialnetwork.services.exception;

public class ArgumentNotValidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ArgumentNotValidException(String msg) {
		super(msg);
	}
}
