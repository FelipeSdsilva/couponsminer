package com.felipesousa.cupomminer.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!prod")
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI StoreAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cupomminer API")
                        .description("This project is for use to validate and manage coupons.")
                        .version("1.0.0")
                        .license(new License()
                                .name("")
                                .url("")));
    }
}
