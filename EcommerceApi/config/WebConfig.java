package com.ws101.delarosa_longcop;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow access to all API endpoints
        registry.addMapping("/api/**")
                // Allow requests from your frontend address
                // If you open your HTML file directly, it might be "file://", or if using live server: "http://localhost:5500"
                .allowedOrigins("http://localhost:5500", "file://") 
                // Allow common HTTP methods
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Allow all headers
                .allowedHeaders("*")
                // Allow sending cookies/authentication data if needed
                .allowCredentials(true);
    }
}