package com.hms.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentReqDto {

    private Long patientId;

    private Long doctorId;

    private LocalDateTime appointmentTime;

    private String reason;

    private String notes;
}
