package noPatterns;

import java.util.Map;

import commons.Request;

public class NoPattern {

	
	public static void main(String[] args) {
		
		var processor = new RequestProcessor();
		
		// criando headers
		var headers = Map.of("Authorization", "Bearer token123");
		var headers2 = Map.of("Content-Type", "application/json");
		
		// criar requisições
		var request1 = new Request("192.168.1.10", headers);
		var request2 = new Request("192.168.1.12", headers2);
		
		try {
			processor.process(request1);
			processor.process(request2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
}
