package modernDesingPatterns;

import commons.Request;

public class BaseRequestProcessor implements RequestProcessor {

	public void baseProcessor(Request request) {
		System.out.println("Request processed successfully");
	}

	@Override
	public void process(Request request) {
		baseProcessor(request);
		
	}

}
