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

    @Query(value = "select a.patient_id, p.name as patient_name,p.email as patient_email,p.phone as patient_phone, a.doctor_id,d.name as doctor_name,d.email as doctor_email,d.phone as doctor_phone,a.id as appointment_id, a.appointment_time, a.reason, a.notes, a.status \n" +
            "from appointment a\n" +
            "         inner join patients p on p.id = a.patient_id\n" +
            "        inner join doctors d on d.id = a.doctor_id where a.patient_id = :patientId",
    nativeQuery = true)
    List<AppointmentDetails> getAllDetailsByPatientId(@Param("patientId") Long patientId);


}
