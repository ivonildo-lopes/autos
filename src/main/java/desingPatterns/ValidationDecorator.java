package desingPatterns;

import com.loja.autos.exceptions.NegocioException;

import commons.Request;

public class ValidationDecorator extends RequestProcessDecorator {

	public ValidationDecorator(RequestProcessor wrapperRequestProcessor) {
		super(wrapperRequestProcessor);
	}
	
	@Override
	public void process(Request request) {
		if(isRequestInvalid(request)) {
			throw new NegocioException("Missing Authorization header");
		}
		
		super.process(request);
	}
	
	private boolean isRequestInvalid(Request request) {
		return request == null || !request.headers().containsKey("Authorization");
	}

}
