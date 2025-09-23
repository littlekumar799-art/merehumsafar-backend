package io.reflectoring.humsafar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
              registry.addMapping("/**")  // allow all paths
                        .allowedOrigins("http://localhost:5173") // frontend origin
//                        .allowedOrigins("https://merehumsafar.netlify.app")
                        .allowedOrigins("http://localhost:5174")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                       .allowCredentials(true);  // for cookies/auth headers

            }
        };
    }
}

