package com.practicas.ia_api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("IA API")
                        .version("v1")
                        .description("Documentación de la API de prácticas")
                        .contact(new Contact().name("Equipo IA").email("soporte@tu-dominio.com"))
                )
                .servers(List.of(new Server().url("http://localhost:8080").description("Generated server url")));
    }
}
