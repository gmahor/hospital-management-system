package com.hms.profile.services;

import com.hms.profile.dto.PatientReqDto;
import com.hms.profile.dto.PatientRespDto;

public interface IPatientService {

    Long addPatient(PatientReqDto patientReqDto);

    PatientRespDto getPatientById(Long id);

}
