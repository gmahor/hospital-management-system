package com.hms.profile.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PublicPathConfig {

    @Bean
    public List<String> publicPaths(){
        return List.of(
                "/profile/patient/addPatient",
                "/profile/doctor/addDoctor",
                "/profile/patient/internal/addPatient",
                "/profile/doctor/internal/addDoctor"
        );
    }


}
