package com.hms.profile.controllers;


import com.hms.profile.dto.PatientReqDto;
import com.hms.profile.dto.PatientRespDto;
import com.hms.profile.services.IPatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/patient")
@RequiredArgsConstructor
public class PatientController {

    public final IPatientService iPatientService;

    @PostMapping("/addPatient")
    public ResponseEntity<Object> addPatient(@Valid @RequestBody PatientReqDto patientReqDto) {
        Long id = iPatientService.addPatient(patientReqDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPatient(@PathVariable Long id) {
        PatientRespDto patient = iPatientService.getPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }


}
