package modernDesingPatterns;

import java.util.Map;

import commons.Request;

public class ModernDecorator {

	public static void main(String[] args) {
		RequestProcessor loggingProcessor = new LoggingProcessor();
		RequestProcessor validationProcessor = new ValidationProcessor();
		RequestProcessor baseProcessor = new BaseRequestProcessor();

		RequestProcessor processors = request -> {
		};

		// Example of chaining processors
		processors = processors.andThen(loggingProcessor).andThen(validationProcessor).andThen(baseProcessor);

		try {
			// criando headers
			var headers = Map.of("Authorization", "Bearer token123");
			var headers2 = Map.of("Content-Type", "application/json");

			// criar requisições
			var request1 = new Request("192.168.1.10", headers);
			var request2 = new Request("192.168.1.12", headers2);

			processors.process(request1);
			processors.process(request2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		

	}

}
