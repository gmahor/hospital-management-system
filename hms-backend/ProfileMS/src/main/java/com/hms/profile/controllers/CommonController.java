package com.hms.profile.controllers;

import com.hms.profile.enums.BloodGroups;
import com.hms.profile.enums.Departments;
import com.hms.profile.enums.Specializations;
import com.hms.profile.services.IDoctorService;
import com.hms.profile.services.IPatientService;
import com.hms.profile.utils.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CommonController {

    private final ResponseHandler responseHandler;

    private final IDoctorService doctorService;

    private final IPatientService patientService;


    @GetMapping("/bloodGroups")
    public ResponseEntity<Object> bloodGroups() {
        List<String> bloodGroups = BloodGroups.getValues();
        return responseHandler.response(bloodGroups, "", true, HttpStatus.OK);
    }

    @GetMapping("/specializations")
    public ResponseEntity<Object> specializations() {
        List<String> specializations = Specializations.getSpecializations();
        return responseHandler.response(specializations, "", true, HttpStatus.OK);
    }

    @GetMapping("/departments")
    public ResponseEntity<Object> departments() {
        List<String> departments = Departments.getDepartments();
        return responseHandler.response(departments, "", true, HttpStatus.OK);
    }

    @GetMapping("/isPatientExist/{id}")
    public ResponseEntity<Object> isPatientExist(@PathVariable("id") Long id) {
        boolean patientExist = patientService.isPatientExist(id);
        return new ResponseEntity<>(patientExist, HttpStatus.OK);

    }

    @GetMapping("/isDoctorExist/{id}")
    public ResponseEntity<Object> isDoctorExist(@PathVariable("id") Long id) {
        boolean doctorExist = doctorService.isDoctorExist(id);
        return new ResponseEntity<>(doctorExist, HttpStatus.OK);

    }


}
