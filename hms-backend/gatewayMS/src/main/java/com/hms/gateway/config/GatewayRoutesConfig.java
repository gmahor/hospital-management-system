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

                .route("USER", r -> r
                        .path("/user/**")
                        .uri("lb://user-ms"))

                .route("PROFILE_PATIENT", r -> r
                        .path("/profile/patient/**")
                        .uri("lb://profile-ms"))

                .route("PROFILE_DOCTOR", r -> r
                        .path("/profile/doctor/**")
                        .uri("lb://profile-ms"))

                .route("PROFILE_API", r -> r
                        .path("/api/**")
                        .uri("lb://profile-ms"))

                .route("APPOINTMENT_API", r -> r
                        .path("/appointment/**")
                        .uri("lb://appointment-ms"))

                .route("APPOINTMENT_GRAPHQL", r -> r
                        .path("/graphql")
                        .uri("lb://appointment-ms"))

                .build();
    }
}