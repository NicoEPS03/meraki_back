package com.meraki.back.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Solicitud de Meraki API",
                version = "1.0",
                description = "Documentación API Meraki"
        )
)
public class SwaggerConfig {

}
