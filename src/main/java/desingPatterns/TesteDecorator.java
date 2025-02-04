package desingPatterns;

import java.util.Map;

import commons.Request;

public class TesteDecorator {

	public static void main(String[] args) {
		
		RequestProcessor baseProcessor = new BaseRequestProcessor(); // aqui é como se fosse o café
		RequestProcessor validationProcessor = new ValidationDecorator(baseProcessor); // aqui é um ingrediente como se fosse um açuçar
		RequestProcessor loggingProcessor = new LoggingDecorator(validationProcessor); // aqui é um ingrediente como se fosse o leite
		RequestProcessor processorPipeline = loggingProcessor; // aqui estou informando por qual decorator vai começar
		
		try {
			// criando headers
			var headers = Map.of("Authorization", "Bearer token123");
			var headers2 = Map.of("Content-Type", "application/json");
			
			// criar requisições
			var request1 = new Request("192.168.1.10", headers);
			var request2 = new Request("192.168.1.12", headers2);
			
			processorPipeline.process(request1);
			processorPipeline.process(request2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
