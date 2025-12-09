package com.loja.autos.exceptions;

public class JwtException extends RuntimeException {

	private static final long serialVersionUID = -3443603880159475743L;
	
	public JwtException(String message) {super(message);};
    public JwtException(String message, Throwable cause) {super(message, cause);};
}
