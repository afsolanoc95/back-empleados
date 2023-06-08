package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/amaris/**") // Ruta del servicio que deseas permitir el acceso
                .allowedOrigins("http://localhost:3000") // Origen permitido (tu aplicación React)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowCredentials(true); // Permitir el envío de cookies y encabezados de autenticación si los usas
    }
}
