package com.hms.profile.controllers;


import com.hms.profile.dto.PatientReqDto;
import com.hms.profile.dto.PatientRespDto;
import com.hms.profile.dto.UpdatePatentReqDto;
import com.hms.profile.services.IPatientService;
import com.hms.profile.utils.ResponseHandler;
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

    private final ResponseHandler responseHandler;

    @PostMapping("/addPatient")
    public ResponseEntity<Object> addPatient(@Valid @RequestBody PatientReqDto patientReqDto) {
        Long id = iPatientService.addPatient(patientReqDto);
        return responseHandler.response(id, "", true, HttpStatus.OK);
    }

    /*
    API for internal service to service communication
    */
    @PostMapping("/internal/addPatient")
    public Long addPatientInternal(@Valid @RequestBody PatientReqDto patientReqDto) {
        return iPatientService.addPatient(patientReqDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPatient(@PathVariable Long id) {
        PatientRespDto patient = iPatientService.getPatientById(id);
        return responseHandler.response(patient, "", true, HttpStatus.OK);
    }

    @PutMapping("/updatePatient")
    public ResponseEntity<Object> updatePatient(@Valid @RequestBody UpdatePatentReqDto updatePatentReqDto) {
        String msg = iPatientService.updatePatient(updatePatentReqDto);
        return responseHandler.response("", msg, true, HttpStatus.OK);
    }


}
