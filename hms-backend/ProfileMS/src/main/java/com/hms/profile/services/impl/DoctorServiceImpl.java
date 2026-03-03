package com.hms.profile.services.impl;

import com.hms.profile.dto.DoctorReqDto;
import com.hms.profile.dto.DoctorRespDto;
import com.hms.profile.entities.Doctor;
import com.hms.profile.exceptions.DoctorAlreadyFoundException;
import com.hms.profile.repositories.DoctorRepository;
import com.hms.profile.services.IDoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements IDoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public Long addDoctor(DoctorReqDto doctorReqDto) {

        boolean existsByEmail = doctorRepository.existsByEmail(doctorReqDto.getEmail());
        if (existsByEmail)
            throw new DoctorAlreadyFoundException("Doctor already exists with the given email: " + doctorReqDto.getEmail());

        boolean existsByNameAndEmail = doctorRepository.existsByNameAndEmail(doctorReqDto.getName(), doctorReqDto.getEmail());
        if (existsByNameAndEmail)
            throw new DoctorAlreadyFoundException(
                    String.format(
                            "Doctor already exists with the given name: %s and email: %s",
                            doctorReqDto.getName(), doctorReqDto.getEmail()
                    )
            );

        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorReqDto, doctor);
        Doctor savedItem = doctorRepository.save(doctor);
        return savedItem.getId();
    }

    @Override
    public DoctorRespDto getDoctorById(Long id) {
        DoctorRespDto doctorRespDto = new DoctorRespDto();
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Doctor not found with id: " + id)
        );
        BeanUtils.copyProperties(doctor, doctorRespDto);
        return doctorRespDto;
    }
}
