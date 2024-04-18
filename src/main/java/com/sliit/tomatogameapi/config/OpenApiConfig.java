package com.sliit.tomatogameapi.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(contact = @Contact(name = "Ravindu Lakshan", email = "", url = ""),
                description = "OpenApi Documentation for Tomato Game API",
                title = "OpenApi Specification for Tomato Game API",
                version = "1.0"
        ),
        servers = {
                @Server(description = "Local ENV", url = "http://localhost:8080"),
        }
)
public class OpenApiConfig {
}
