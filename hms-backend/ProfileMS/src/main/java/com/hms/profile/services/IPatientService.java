package com.hms.profile.services;

import com.hms.profile.dto.PatientReqDto;
import com.hms.profile.dto.PatientRespDto;
import com.hms.profile.dto.UpdatePatentReqDto;

public interface IPatientService {

    Long addPatient(PatientReqDto patientReqDto);

    PatientRespDto getPatientById(Long id);

    String updatePatient(UpdatePatentReqDto updatePatentDto);

    boolean isPatientExist(long  id);

}
