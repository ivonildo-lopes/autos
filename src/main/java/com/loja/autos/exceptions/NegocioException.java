package com.loja.autos.exceptions;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -2265007930060446140L;
	
	public NegocioException(String message) {super(message);};
    public NegocioException(String message, Throwable cause) {super(message, cause);};
}
