package com.hms.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hms.dto.DoctorRespDto;
import com.hms.dto.PatientRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

    private final WebClient.Builder webClient;

    public boolean patientExists(long id, String token) {
        return Boolean.TRUE.equals(webClient.build()
                .get()
                .uri("http://localhost:9000/api/isPatientExist/" + id)
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
                .uri("http://localhost:9000/api/isDoctorExist/" + id)
                .header("AUTHORIZATION", token)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException(errorBody)))
                ).bodyToMono(Boolean.class)
                .block());
    }

    public PatientRespDto patientDetails(long id, String token) throws Exception {
        PatientRespDto patientRespDto = null;
        String resp = webClient.build()
                .get()
                .uri("http://localhost:9000//profile/patient/" + id)
                .header("AUTHORIZATION", token)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException(errorBody)))
                ).bodyToMono(String.class)
                .block();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonObject = mapper.readTree(resp);
        boolean isSuccess = jsonObject.get("isSuccess").asBoolean();
        int statusCode = jsonObject.get("statusCode").asInt();
        if (isSuccess && statusCode == 200) {
            patientRespDto = mapper.treeToValue(jsonObject.get("data"), PatientRespDto.class);
        }
        return patientRespDto;
    }


    public DoctorRespDto doctorDetails(long id, String token) throws Exception {
        DoctorRespDto doctorRespDto = null;
        String resp = webClient.build()
                .get()
                .uri("http://localhost:9000//profile/doctor/" + id)
                .header("AUTHORIZATION", token)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException(errorBody)))
                ).bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonObject = mapper.readTree(resp);
        boolean isSuccess = jsonObject.get("isSuccess").asBoolean();
        int statusCode = jsonObject.get("statusCode").asInt();
        if (isSuccess && statusCode == 200) {
            doctorRespDto = mapper.treeToValue(jsonObject.get("data"), DoctorRespDto.class);
        }
        return doctorRespDto;
    }
}
