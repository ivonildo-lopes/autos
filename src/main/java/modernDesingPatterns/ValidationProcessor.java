package modernDesingPatterns;

import com.loja.autos.exceptions.NegocioException;

import commons.Request;

public class ValidationProcessor implements RequestProcessor {

	public void validationProcessor(Request request) {
		if(isRequestInvalid(request)) {
			throw new NegocioException("Missing Authorization header");
		}
	}
	
	private boolean isRequestInvalid(Request request) {
		return request == null || !request.headers().containsKey("Authorization");
	}

	@Override
	public void process(Request request) {
		validationProcessor(request);
		
	}

}
