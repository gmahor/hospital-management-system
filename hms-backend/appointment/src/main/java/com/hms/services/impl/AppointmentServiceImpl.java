package com.hms.services.impl;

import com.hms.dto.AppointmentReqDto;
import com.hms.dto.AppointmentRespDto;
import com.hms.dto.DoctorRespDto;
import com.hms.dto.PatientRespDto;
import com.hms.entities.Appointment;
import com.hms.enums.AppointmentStatus;
import com.hms.exceptions.AppointmentException;
import com.hms.repositories.AppointmentRepository;
import com.hms.services.AppointmentService;
import com.hms.services.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final ExternalApiService apiService;

    @Override
    public String scheduledAppointment(AppointmentReqDto appointmentReqDto, String token) {
        boolean isPatientExists = apiService.patientExists(appointmentReqDto.getPatientId(), token);
        if (!isPatientExists) {
            throw new AppointmentException("Patient not found");
        }

        boolean isDoctorExists = apiService.doctorExists(appointmentReqDto.getDoctorId(), token);
        if (!isDoctorExists) {
            throw new AppointmentException("Doctor not found");
        }

        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(appointmentReqDto, appointment);
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        appointmentRepository.save(appointment);
        return "Appointment Scheduled Successfully";
    }

    @Override
    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentException("Appointment Not Found"));
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    @Override
    public void completeAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentException("Appointment Not Found"));
        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointmentRepository.save(appointment);
    }

    @Override
    public void rescheduleAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentException("Appointment Not Found"));
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        appointmentRepository.save(appointment);
    }

    @Override
    public AppointmentRespDto getAppointmentById(Long appointmentId) {
        AppointmentRespDto appointmentRespDto = new AppointmentRespDto();
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentException("Appointment Not Found"));
        BeanUtils.copyProperties(appointment, appointmentRespDto);
        appointmentRespDto.setStatus(appointment.getStatus().toString());
        return appointmentRespDto;
    }

    @Override
    public Map<String, Object> getAppointmentWithPatientAndDoctorDetails(long appointmentId, String token) throws Exception {
        Map<String,Object> map  = new HashMap<>();
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentException("Appointment Not Found"));
        PatientRespDto patientRespDto = apiService.patientDetails(appointment.getPatientId(), token);
        DoctorRespDto doctorRespDto = apiService.doctorDetails(appointment.getDoctorId(), token);
        apiService.patientDetails(appointment.getPatientId(), token);
        apiService.doctorDetails(appointment.getDoctorId(), token);
        map.put("appointment_details",appointment);
        map.put("patient_details",patientRespDto);
        map.put("doctor_details",doctorRespDto);
        return map;
    }
}
