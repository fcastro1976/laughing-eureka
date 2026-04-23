package com.newdex.services.inclusivecontent.config.swagger;

@org.springframework.context.annotation.Configuration()
@io.swagger.v3.oas.annotations.OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "Inclusive Content service REST API OpenAPI docs"), servers = {@io.swagger.v3.oas.annotations.servers.Server(url = "http://localhost:8085")})
@org.springframework.context.annotation.Profile(value = {"dev", "stage"})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/newdex/services/inclusivecontent/config/swagger/SwaggerUIConfig;", "", "()V", "inclusive-content-service"})
public class SwaggerUIConfig {
    
    public SwaggerUIConfig() {
        super();
    }
}