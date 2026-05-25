package com.hms.profile.controllers;

import com.hms.profile.constants.MessageConstant;
import com.hms.profile.dto.DoctorReqDto;
import com.hms.profile.dto.DoctorRespDto;
import com.hms.profile.dto.DoctorsDropdown;
import com.hms.profile.dto.UpdateDoctorReqDto;
import com.hms.profile.services.IDoctorService;
import com.hms.profile.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/profile/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final IDoctorService iDoctorService;

    private final ResponseHandler responseHandler;

    @PostMapping("/addDoctor")
    ResponseEntity<Object> addDoctor(@Valid @RequestBody DoctorReqDto doctorReqDto) {
        Long id = iDoctorService.addDoctor(doctorReqDto);
        return responseHandler.response(id, "", true, HttpStatus.OK);
    }

    /*
     API for internal service to service communication
     */
    @PostMapping("/internal/addDoctor")
    Long addDoctorInternal(@Valid @RequestBody DoctorReqDto doctorReqDto) {
        return iDoctorService.addDoctor(doctorReqDto);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getDoctor(@PathVariable Long id) {
        DoctorRespDto doctor = iDoctorService.getDoctorById(id);
        return responseHandler.response(doctor, "", true, HttpStatus.OK);
    }

    @PutMapping("/updateDoctorDetails")
    public ResponseEntity<Object> updateDoctorDetails(@Valid @RequestBody UpdateDoctorReqDto updateDoctorReqDto) {
        String msg = iDoctorService.updateDoctorDetails(updateDoctorReqDto);
        return responseHandler.response("", msg, true, HttpStatus.OK);
    }

    @GetMapping("/getDoctorsDropdown")
    public ResponseEntity<Object> getDoctorsDropdown() {
        try {
            List<DoctorsDropdown> doctorsName = iDoctorService.getDoctorsName();
            return responseHandler.response(doctorsName, MessageConstant.DOCTORS_NAMES_FOUND, true, HttpStatus.OK);
        } catch (Exception e) {
            log.error(MessageConstant.ERROR_WHILE_GETTING_DOCTORS_NAMES + ": ", e);
            return responseHandler.response("", MessageConstant.ERROR_WHILE_GETTING_DOCTORS_NAMES, true, HttpStatus.OK);
        }
    }


}
