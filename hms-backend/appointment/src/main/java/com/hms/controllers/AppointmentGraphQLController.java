package com.hms.controllers;

import com.hms.dto.AppointmentRespDto;
import com.hms.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AppointmentGraphQLController {

    private final AppointmentService appointmentService;

    @QueryMapping
    public AppointmentRespDto getAppointmentById(@Argument Long id) {
        return appointmentService.getAppointmentById(id);
    }

}
