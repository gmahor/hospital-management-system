package com.hms.profile.services.impl;

import com.hms.profile.dto.PatientReqDto;
import com.hms.profile.dto.PatientRespDto;
import com.hms.profile.dto.UpdatePatentReqDto;
import com.hms.profile.entities.Patient;
import com.hms.profile.exceptions.PatientException;
import com.hms.profile.exceptions.PatientNotFoundException;
import com.hms.profile.repositories.PatientRepository;
import com.hms.profile.services.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService {

    public final PatientRepository patientRepository;

    @Override
    public Long addPatient(PatientReqDto patientReqDto) {
        boolean isPatientExists = patientRepository.existsByEmail(patientReqDto.getEmail());
        if (isPatientExists)
            throw new PatientException("Patient already exists with the given email: " + patientReqDto.getEmail());
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientReqDto, patient);
        Patient savedItem = patientRepository.save(patient);
        return savedItem.getId();
    }

    @Override
    public PatientRespDto getPatientById(Long id) {
        PatientRespDto patientRespDto = new PatientRespDto();
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundException("Patient not found with id: " + id)
                );
        BeanUtils.copyProperties(patient, patientRespDto);
        return patientRespDto;
    }

    @Override
    public String updatePatient(UpdatePatentReqDto updatePatentDto) {
        Patient patient = patientRepository.findById(updatePatentDto.getId())
                .orElseThrow(() ->
                        new PatientNotFoundException("Patient not found with id: " + updatePatentDto.getId())
                );
        BeanUtils.copyProperties(updatePatentDto, patient);
        patientRepository.save(patient);
        return "Patient updated successfully";
    }


}
