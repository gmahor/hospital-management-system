package com.hms.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hms.dto.DoctorRespDto;
import com.hms.dto.PatientRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

    @Value("${profile.service-url}")
    private String profileServiceUrl;

    @Value("${appointment.service-url}")
    private String appointmentServiceUrl;

    private final WebClient.Builder webClient;

    public boolean patientExists(long id, String token) {
        return Boolean.TRUE.equals(webClient.build()
                .get()
                .uri(profileServiceUrl + "/api/isPatientExist/" + id)   // ✅ use profileServiceUrl
                .header("AUTHORIZATION", token)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException(errorBody)))
                ).bodyToMono(Boolean.class)
                .block());
    }

    public boolean doctorExists(long id, String token) {
        return Boolean.TRUE.equals(webClient.build()
                .get()
                .uri(profileServiceUrl + "/api/isDoctorExist/" + id)   // ✅ use profileServiceUrl
                .header("AUTHORIZATION", token)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException(errorBody)))
                ).bodyToMono(Boolean.class)
                .block());
    }

    public PatientRespDto patientDetails(long id, String token) throws Exception {
        String resp = webClient.build()
                .get()
                .uri(profileServiceUrl + "/profile/patient/" + id)   // ✅ use profileServiceUrl
                .header("AUTHORIZATION", token)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException(errorBody)))
                ).bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonObject = mapper.readTree(resp);
        if (jsonObject.get("isSuccess").asBoolean() && jsonObject.get("statusCode").asInt() == 200) {
            return mapper.treeToValue(jsonObject.get("data"), PatientRespDto.class);
        }
        return null;
    }

    public DoctorRespDto doctorDetails(long id, String token) throws Exception {
        String resp = webClient.build()
                .get()
                .uri(profileServiceUrl + "/profile/doctor/" + id)   // ✅ use profileServiceUrl
                .header("AUTHORIZATION", token)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException(errorBody)))
                ).bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonObject = mapper.readTree(resp);
        if (jsonObject.get("isSuccess").asBoolean() && jsonObject.get("statusCode").asInt() == 200) {
            return mapper.treeToValue(jsonObject.get("data"), DoctorRespDto.class);
        }
        return null;
    }
}