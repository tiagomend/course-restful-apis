package br.com.tiagomendonca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
            .title("REST API's RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")
            .version("v1")
            .description("REST API's RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")
            .termsOfService("https://github.com/tiagomend/course-restful-apis")
            .license(new License()
                .name("MIT")
                .url("https://github.com/tiagomend/course-restful-apis"))
            );
    }
}
