package com.hms.profile.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateDoctorReqDto {

    @Min(value = 1,message = "Id Is Required.")
    private Long id;

    @NotBlank(message = "Dob Is Required.")
    private String dob;

    @NotBlank(message = "Phone Is Required.")
    private String phone;

    @NotBlank(message = "Address Is Required.")
    private String address;

    @NotBlank(message = "License No Is Required.")
    private String licenseNo;

    @NotBlank(message = "Specialization Is Required.")
    private String specialization;

    @NotBlank(message = "Department Is Required.")
    private String department;

    @NotBlank(message = "Total Experience Is Required.")
    private String totalExperience;
}
