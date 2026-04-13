package com.hms.controllers;

import com.hms.dto.AppointmentReqDto;
import com.hms.dto.AppointmentRespDto;
import com.hms.exceptions.AppointmentException;
import com.hms.services.AppointmentService;
import com.hms.services.ExternalApiService;
import com.hms.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/appointment")
@Validated
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final ResponseHandler responseHandler;

    private final ExternalApiService apiService;

    @PostMapping("/scheduledAppointment")
    public ResponseEntity<Object> scheduledAppointment(@Valid @RequestBody AppointmentReqDto appointmentReqDto,
                                                       @RequestHeader(value = "Authorization", required = false) String token) {
        try {
            if (token.isEmpty()) {
                return responseHandler.response("", "Token is not found in the header", false, HttpStatus.NOT_FOUND);
            }
            String msg = appointmentService.scheduledAppointment(appointmentReqDto, token);
            return responseHandler.response("", msg, true, HttpStatus.OK);
        } catch (AppointmentException e) {
            log.error(e.getMessage());
            return responseHandler.response("", e.getMessage(), false, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseHandler.response("", "Error while scheduling appointment", false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAppointmentById(@PathVariable("id") Long id) {
        try {
            AppointmentRespDto appointment = appointmentService.getAppointmentById(id);
            return responseHandler.response(appointment, "Appointment fetched successfully", true, HttpStatus.OK);
        } catch (AppointmentException e) {
            log.error(e.getMessage());
            return responseHandler.response("", e.getMessage(), false, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseHandler.response("", "Error while fetching appointment", false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/patient_and_doctor_details_appointment/{id}")
    public ResponseEntity<Object> getAppointmentWithPatientAndDoctorDetails(@PathVariable("id") long id,
                                                                            @RequestHeader(value = "Authorization", required = false) String token) throws Exception {
        try {
            Map<String, Object> details = appointmentService.getAppointmentWithPatientAndDoctorDetails(id, token);
            return responseHandler.response(details, null, true, HttpStatus.OK);
        } catch (AppointmentException e) {
            log.error(e.getMessage());
            return responseHandler.response("", e.getMessage(), false, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseHandler.response("", "Error while fetching appointment with patient and doctor details", false, HttpStatus.BAD_REQUEST);
        }
    }

}
