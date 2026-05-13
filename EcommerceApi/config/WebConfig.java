// src/main/java/com/ws101/delarosa_longcop/EcommerceApi/config/WebConfig.java
package com.ws101.delarosa_longcop.EcommerceApi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for handling Cross-Origin Resource Sharing (CORS) settings.
 * This class implements WebMvcConfigurer to customize Spring MVC's configuration,
 * specifically for allowing requests from different origins (domains/ports).
 */
@Configuration // Indicates that this class provides Spring Bean definitions and configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS mappings for the application.
     * This method is overridden from WebMvcConfigurer to specify which origins,
     * HTTP methods, and headers are allowed for cross-origin requests.
     *
     * @param registry The CorsRegistry to configure CORS rules.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow access to all API endpoints that start with /api/
        // You might change this to "/**" to allow all paths, or "/your-specific-path/**" for specific paths.
        registry.addMapping("/api/**")
                // Allow requests from specified frontend addresses
                // "http://localhost:5500" is a common address for frontend development servers.
                // "file://" allows direct opening of HTML files in a browser (useful during local development).
                .allowedOrigins("http://localhost:5500", "file://") // Replace 5500 if your frontend uses a different port
                // Allow common HTTP methods for requests
                // "OPTIONS" is crucial for handling CORS pre-flight requests.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Allow all HTTP headers to be sent in cross-origin requests
                // Alternatively, you can specify specific headers like "Authorization", "Content-Type".
                .allowedHeaders("*")
                // Allow sending cookies, HTTP authentication, or SSL certificates with cross-origin requests
                // Set to true if your frontend sends credentials (e.g., for authentication).
                .allowCredentials(true)
                // Set the maximum age (in seconds) for the pre-flight response cache
                // This reduces the number of pre-flight requests sent by the browser.
                .maxAge(3600); // 1 hour
    }
}