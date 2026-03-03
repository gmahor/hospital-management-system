package com.hms.profile.controllers;

import com.hms.profile.dto.DoctorReqDto;
import com.hms.profile.dto.DoctorRespDto;
import com.hms.profile.services.IDoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final IDoctorService iDoctorService;

    @PostMapping("/addDoctor")
    ResponseEntity<Object> addDoctor(@Valid @RequestBody DoctorReqDto doctorReqDto) {
        Long id = iDoctorService.addDoctor(doctorReqDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getDoctor(@PathVariable Long id) {
        DoctorRespDto doctor = iDoctorService.getDoctorById(id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
}
