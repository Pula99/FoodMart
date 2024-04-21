package com.foodmart.app.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info =  @Info (
                contact = @Contact (
                        name = "Kusal Bandara",
                        email = "gpbandarakusal@gmail.com"
                ),
                description = "OpenApi documentation for FoodMart",
                title = "OpenApi specification - FoodMart",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local Envo",
                        url = "http://localhost:8080"
                )
        }
)

public class OpenApiConfig {
}
