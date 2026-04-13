package com.hms.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientRespDto {

    private Long id;

    private String name;

    private String email;

    private String dob;

    private String phone;

    private String address;

    private String aadhaarNo;

    private String bloodGroup;

    private String allergies;

    private String chronicDisease;

}
