package modernDesingPatterns;

import commons.Request;

@FunctionalInterface
public interface RequestProcessor {
	
	void process(Request request);
	
	// compor processadores - decorators
	
	default RequestProcessor andThen(RequestProcessor next) {
		return request -> {
			this.process(request);
			next.process(request);
		};
	}

}
