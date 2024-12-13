package com.loja.autos.exceptions;

public class BadValueException extends RuntimeException {

	private static final long serialVersionUID = -3203896620981193623L;
	
	public BadValueException(String message) {super(message);};
    public BadValueException(String message, Throwable cause) {super(message, cause);};
}
