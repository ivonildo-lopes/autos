package desingPatterns;

import commons.Request;

public class BaseRequestProcessor implements RequestProcessor {

	@Override
	public void process(Request request) {
		System.out.println("Request processed successfully");
	}

}
