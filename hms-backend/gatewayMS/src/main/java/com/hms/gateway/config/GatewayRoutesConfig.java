package com.hms.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Value("${uri.user-service-url}")
    private String userServiceUrl;

    @Value("${uri.profile-service-url}")
    private String profileServiceUrl;

    @Value("${uri.appointment-service-url}")
    private String appointmentServiceUrl;

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("UserMS", r -> r.path("/user/**")
                        .uri(userServiceUrl))

                .route("ProfileMS-PATIENT", r -> r.path("/profile/patient/**")
                        .uri(profileServiceUrl))

                .route("ProfileMS-DOCTOR", r -> r.path("/profile/doctor/**")
                        .uri(profileServiceUrl))

                .route("ProfileMS-API", r -> r.path("/api/**")
                        .uri(profileServiceUrl))

                .route("AppointmentMS-API", r -> r.path("/appointment/**")
                        .uri(appointmentServiceUrl))

                .route("AppointmentMS-GraphQL", r -> r.path("/graphql")
                        .uri(appointmentServiceUrl))

                .build();
    }
}