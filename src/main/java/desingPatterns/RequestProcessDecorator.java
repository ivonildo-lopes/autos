package desingPatterns;

import commons.Request;

public abstract class RequestProcessDecorator implements RequestProcessor {
	
	protected final RequestProcessor wrapperRequestProcessor;
	
	public RequestProcessDecorator(RequestProcessor wrapperRequestProcessor) {
		this.wrapperRequestProcessor = wrapperRequestProcessor;
	}
	
	@Override
	public void process(Request request) {
		wrapperRequestProcessor.process(request);
	}
}
