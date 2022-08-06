package com.finalProject.ApiGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public RouteLocator getRoutes(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(p->p
                        .path("/user/**")
                        .uri("http://localhost:64200/*"))
                .route(p->p
                        .path("/boardTask/**")
                        .uri("http://localhost:65001/*"))
                .route(p->p
                        .path("/userBoard/**")
                        .uri("http://localhost:65001/*"))

                .build();
    }
}
