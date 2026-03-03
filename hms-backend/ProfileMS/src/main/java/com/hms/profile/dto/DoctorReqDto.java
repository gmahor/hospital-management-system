package com.hms.profile.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class DoctorReqDto {

    @NotBlank(message = "Name is Required.")
    private String name;

    @NotBlank(message = "Email Is Required.")
    private String email;

//    @NotBlank(message = "Dob Is Required(yyyy-mm-dd).")
    private LocalDate dob;

    private String phone;

    private String address;

    private String licenseNo;

    private String specialization;

    private String department;

    private Integer totalExperience;

}
