package com.hms.profile.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "patients", indexes = {
        @Index(name = "idx_patient_id", columnList = "id")
})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private LocalDate dob;

    private String phone;

    private String address;

    @Column(unique = true)
    private String aadhaarNo;

    private String bloodGroup;

    private String allergies;

    private String chronicDisease;

}
