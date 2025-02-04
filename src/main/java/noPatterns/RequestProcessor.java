package noPatterns;

import com.loja.autos.exceptions.NegocioException;

import commons.Request;

public class RequestProcessor {
	
	public void process(Request request) {
		// log da requisição
		System.out.println("Logging request: " + request);
		
		//validação
		if(isRequestInvalid(request)) {
			throw new NegocioException("Missing Authorization header");
		}
		
		//processamento
		System.out.println("Request processed successfully");
	}

	private boolean isRequestInvalid(Request request) {
		return request == null || !request.headers().containsKey("Authorization");
	}

}
