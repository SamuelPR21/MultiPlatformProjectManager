package com.example.samu.manager.config.security

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry


@Configuration
class CorsConfig {

    fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/")
            .allowedOrigins("*") // Permitir desde cualquier origen
            .allowedMethods("*") // Permitir todos los métodos HTTP
    }
}