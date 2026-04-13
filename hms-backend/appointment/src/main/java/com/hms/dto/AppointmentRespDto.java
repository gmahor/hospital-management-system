package com.hms.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRespDto {

    private Long patientId;

    private Long doctorId;

    private LocalDateTime appointmentTime;

    private String status;

    private String reason;

    private String notes;
}
