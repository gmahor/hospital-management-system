package com.hms.profile.dto;


import com.hms.profile.enums.BloodGroups;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientRespDto {

    private Long id;

    private String name;

    private String email;

    private LocalDate dob;

    private String phone;

    private String address;

    private String aadhaarNo;

    private BloodGroups bloodGroup;

}
