package com.loja.autos.config;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Api Autos", 
                                description = "API controle de autos.", 
                                version = "v0.0.1"))
public class OpenApiConfig {
	
	@Value("classpath:springdoc-responses/responses.json")
	private Resource jsonFile;

    @Bean
    OpenAPI customOpenAPI() throws IOException {
    	
    	var components = new Components();
    	
    	//Adiciona os tipos de resposta à documentação.
        components.addResponses("badRequest", getResponse("badRequestResponse", "BAD REQUEST"));
        components.addResponses("notFound", getResponse("notFoundResponse", "NOT FOUND"));
        components.addResponses("unauthorized", getResponse("unauthorizedResponse", "UNAUTHORIZED"));
        components.addResponses("forbidden", getResponse("forbiddenResponse", "FORBIDDEN"));
        components.addResponses("internalServerError", getResponse("internalServerErrorResponse", "INTERNAL SERVER ERROR"));
    	
        components
        	.addSecuritySchemes("bearerKey", new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
            );
        
        return new OpenAPI()
            .components(components)
            .security(List.of(new SecurityRequirement().addList("bearerKey")));
    }

	private ApiResponse getResponse(String campoJsonResponses, String descricaoStatusCodeHttp) throws IOException {
		return new ApiResponse().content(
    	        new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
    	            new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
    	                new Example().value(read(campoJsonResponses))))
    	    ).description(descricaoStatusCodeHttp);
	}
    
    private String read(String key) throws IOException {
        try (var inputStream = jsonFile.getInputStream()) {
          String content = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
          return new JSONObject(content).get(key).toString();
        }
      }
}