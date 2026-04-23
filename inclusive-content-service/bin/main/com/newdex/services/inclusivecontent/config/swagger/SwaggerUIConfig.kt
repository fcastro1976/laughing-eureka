package com.newdex.services.inclusivecontent.config.swagger

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "Inclusive Content service REST API OpenAPI docs",
    ),
    servers = [
        Server(url = "http://localhost:8085"),
    ],
)
@Profile("dev", "stage")
class SwaggerUIConfig
