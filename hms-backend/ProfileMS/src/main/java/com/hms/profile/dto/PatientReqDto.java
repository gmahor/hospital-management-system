package com.hms.profile.dto;

import com.hms.profile.enums.BloodGroups;
import com.hms.profile.utils.BloodGroupConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientReqDto {

    @NotBlank(message = "Name Is Required.")
    private String name;

    @NotBlank(message = "Email Is Required.")
    private String email;

//    @NotBlank(message = "Dob Is Required.")
    private LocalDate dob;

//    @NotBlank(message = "Phone Is Required.")
    private String phone;

//    @NotBlank(message = "Address Is Required.")
    private String address;

//    @NotBlank(message = "Aadhaar No Is Required.")
    private String aadhaarNo;

//    @NotBlank(message = "Blood Group Is Required.")
    private String bloodGroup;

    private String allergies;

    private String chronicDisease;

}
