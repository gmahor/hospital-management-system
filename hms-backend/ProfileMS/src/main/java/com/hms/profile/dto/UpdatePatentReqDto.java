package com.hms.profile.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdatePatentReqDto {

    @Min(value = 1,message = "Id Is Required.")
    private Long id;

    @NotBlank(message = "Dob Is Required.")
    private LocalDate dob;

    @NotBlank(message = "Phone Is Required.")
    private String phone;

    @NotBlank(message = "Address Is Required.")
    private String address;

    @NotBlank(message = "Aadhaar No Is Required.")
    private String aadhaarNo;

    @NotBlank(message = "Blood Group Is Required.")
    private String bloodGroup;

    @NotBlank(message = "Allergies Is Required.")
    private String allergies;

    @NotBlank(message = "Chronic Disease Is Required.")
    private String chronicDisease;

}
