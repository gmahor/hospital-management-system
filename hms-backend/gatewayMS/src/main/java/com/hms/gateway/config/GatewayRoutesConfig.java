package com.hms.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("UserMS", r -> r.path("/user/**")
                        .uri("http://localhost:8080"))
                .route("ProfileMS-PATIENT", r -> r.path("/profile/patient/**")
                        .uri("http://localhost:8081"))
                .route("ProfileMS-DOCTOR", r -> r.path("/profile/doctor/**")
                        .uri("http://localhost:8081"))
                .route("ProfileMS-API", r -> r.path("/api/**")
                        .uri("http://localhost:8081"))
                .route("AppointmentMS-API", r -> r.path("/appointment/**")
                        .uri("http://localhost:8082"))
                .route("AppointmentMS-GraphQL", r -> r.path("/graphql")
                        .uri("http://localhost:8082"))

                .build();
    }


}
