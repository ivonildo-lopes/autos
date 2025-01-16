package com.loja.autos.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Api Autos", 
                                description = "API controle de autos.", 
                                version = "v0.0.1"))
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components()
                .addSecuritySchemes("basicScheme", 
                     new SecurityScheme()
                         .type(SecurityScheme.Type.HTTP)
                         .scheme("basic"))	
                .addSecuritySchemes("bearerAuth",
                     new SecurityScheme()
                         .type(SecurityScheme.Type.HTTP)
                         .scheme("bearer")
                         .bearerFormat("JWT"))
            );
    }
}