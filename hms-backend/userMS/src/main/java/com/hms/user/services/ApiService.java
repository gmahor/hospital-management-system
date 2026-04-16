package com.hms.user.services;

import com.hms.user.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ApiService {

    @Value("${gateway.service-url}")
    private String gatewayServiceUrl;

    private final WebClient.Builder webClient;

    public Long addProfile(UserDto userDto) {
        System.out.println("gatewayServiceUrl : " + gatewayServiceUrl);
        return switch (userDto.getRole()) {
            case "ADMIN" -> null;
            case "DOCTOR" -> webClient.build()
                    .post()
                    .uri(gatewayServiceUrl + "/profile/doctor/internal/addDoctor")
                    .bodyValue(userDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, clientResponse ->
                            clientResponse.bodyToMono(String.class)
                                    .flatMap(errorBody -> Mono.error(new RuntimeException(errorBody)))
                    )
                    .bodyToMono(Long.class)
                    .block();
            case "PATIENT" -> webClient.build()
                    .post()
                    .uri(gatewayServiceUrl + "/profile/patient/internal/addPatient")
                    .bodyValue(userDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, clientResponse ->
                            clientResponse.bodyToMono(String.class)
                                    .flatMap(errorBody -> Mono.error(new RuntimeException(errorBody)))
                    )
                    .bodyToMono(Long.class)
                    .block();
            default -> throw new RuntimeException("Invalid role: " + userDto.getRole());
        };

    }

}
