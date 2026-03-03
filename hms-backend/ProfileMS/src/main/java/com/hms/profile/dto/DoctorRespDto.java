package com.hms.profile.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class DoctorRespDto {

    private Long id;

    private String name;

    private String email;

    private LocalDate dob;

    private String phone;

    private String address;

    private String licenseNo;

    private String specialization;

    private String department;

    private Integer totalExperience;

}
