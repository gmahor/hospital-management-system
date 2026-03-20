package com.hms.user.services;

import com.hms.user.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final WebClient.Builder webClient;

    public Long addProfile(UserDto userDto) {
        return switch (userDto.getRole()) {
            case "ADMIN" -> null;
            case "DOCTOR" -> webClient.build()
                    .post()
                    .uri("http://localhost:9000/profile/doctor/internal/addDoctor")
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
                    .uri("http://localhost:9000/profile/patient/internal/addPatient")
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
