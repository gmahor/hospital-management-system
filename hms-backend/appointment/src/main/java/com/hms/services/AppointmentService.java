package com.hms.services;

import com.hms.dto.AppointmentReqDto;
import com.hms.dto.AppointmentRespDto;
import com.hms.dto.ValueInterface.AppointmentDetails;

import java.util.List;
import java.util.Map;

public interface AppointmentService {

    String scheduledAppointment(AppointmentReqDto appointment, String token);

    void cancelAppointment(Long appointmentId);

    void completeAppointment(Long appointmentId);

    void rescheduleAppointment(Long appointmentId);

    AppointmentRespDto getAppointmentById(Long appointmentId);

    Map<String, Object> getAppointmentWithPatientAndDoctorDetails(long appointmentId, String token) throws Exception;

    List<String> getAllAppointmentReasons();

    List<AppointmentDetails> getAllAppointmentsByPatientId(Long patientId);
}
