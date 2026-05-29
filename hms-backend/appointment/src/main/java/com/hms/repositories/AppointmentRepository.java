package com.hms.repositories;

import com.hms.dto.ValueInterface.AppointmentDetails;
import com.hms.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "select a.patient_id as patientId, " +
            "p.name as patientName, p.email as patientEmail, p.phone as patientPhone, " +
            "a.doctor_id as doctorId, d.name as doctorName, d.email as doctorEmail, d.phone as doctorPhone, " +
            "a.id as appointmentId, a.appointment_time as appointmentTime, a.reason as reason, a.notes as notes, a.status as status " +
            "from appointment a " +
            "inner join patients p on p.id = a.patient_id " +
            "inner join doctors d on d.id = a.doctor_id " +
            "where a.patient_id = :patientId",
            nativeQuery = true)
    List<AppointmentDetails> getAllDetailsByPatientId(@Param("patientId") Long patientId);


}
