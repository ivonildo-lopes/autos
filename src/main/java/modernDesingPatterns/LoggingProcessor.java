package modernDesingPatterns;

import commons.Request;

public class LoggingProcessor implements RequestProcessor {
	
	public void loggingProcessor(Request request) {
		System.out.println("Logging request: " + request);
	}

	@Override
	public void process(Request request) {
		System.out.println("Logging request: " + request);
	}

}
