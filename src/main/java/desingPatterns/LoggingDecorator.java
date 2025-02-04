package desingPatterns;

import commons.Request;

public class LoggingDecorator extends RequestProcessDecorator {

	public LoggingDecorator(RequestProcessor wrapperRequestProcessor) {
		super(wrapperRequestProcessor);
	}
	
	@Override
	public void process(Request request) {
		System.out.println("Logging request: " + request);
		super.process(request);
	}

}
