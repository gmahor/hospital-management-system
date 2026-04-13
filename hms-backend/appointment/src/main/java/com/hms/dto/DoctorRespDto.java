package com.hms.dto;


import lombok.Data;

@Data
public class DoctorRespDto {

    private Long id;

    private String name;

    private String email;

    private String dob;

    private String phone;

    private String address;

    private String licenseNo;

    private String specialization;

    private String department;

    private Integer totalExperience;

}
