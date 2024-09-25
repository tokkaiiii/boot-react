package com.backend

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        val jwt: String = "JWT"
        val securityRequirement: SecurityRequirement? = SecurityRequirement().addList(jwt)
        val components = Components().addSecuritySchemes(jwt, SecurityScheme()
            .name(jwt)
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
        )
         return OpenAPI()
             .components(components)
             .info(apiInfo())
             .addSecurityItem(securityRequirement)
             .components(components)
    }

    fun apiInfo(): Info{
        return Info()
            .title("API Test") // API의 제목
            .description("practice swagger UI") //API에 대한 설명
            .version("1.0.0")
    }
}