package com.hms.profile.controllers;

import com.hms.profile.enums.BloodGroups;
import com.hms.profile.enums.Departments;
import com.hms.profile.enums.Specializations;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CommonController {


    @GetMapping("/bloodGroups")
    public ResponseEntity<Object> bloodGroups() {
        List<String> bloodGroups = BloodGroups.getValues();
        return ResponseEntity.status(HttpStatus.OK).body(bloodGroups);
    }

    @GetMapping("/specializations")
    public ResponseEntity<Object> specializations() {
        List<String> specializations = Specializations.getSpecializations();
        return ResponseEntity.status(HttpStatus.OK).body(specializations);
    }

    @GetMapping("/departments")
    public ResponseEntity<Object> departments() {
        List<String> departments = Departments.getDepartments();
        return ResponseEntity.status(HttpStatus.OK).body(departments);
    }


}
