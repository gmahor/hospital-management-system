package com.hms.profile.services.impl;

import com.hms.profile.dto.DoctorReqDto;
import com.hms.profile.dto.DoctorRespDto;
import com.hms.profile.dto.UpdateDoctorReqDto;
import com.hms.profile.entities.Doctor;
import com.hms.profile.exceptions.DoctorAlreadyFoundException;
import com.hms.profile.repositories.DoctorRepository;
import com.hms.profile.services.IDoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
        doctorRespDto.setDob(doctor.getDob().toString());
        return doctorRespDto;
    }

    @Override
    public String updateDoctorDetails(UpdateDoctorReqDto updateDoctorReqDto) {
        Doctor doctor = doctorRepository.findById(updateDoctorReqDto.getId()).orElseThrow(() ->
                new RuntimeException("Doctor not found with id: " + updateDoctorReqDto.getId())
        );
        BeanUtils.copyProperties(updateDoctorReqDto, doctor);
        doctor.setDob(LocalDate.parse(updateDoctorReqDto.getDob()));
        doctor.setTotalExperience(Integer.parseInt(updateDoctorReqDto.getTotalExperience()));
        doctorRepository.save(doctor);
        return "Doctor updated successfully";
    }

    @Override
    public boolean isDoctorExist(long id) {
        return doctorRepository.existsById(id);
    }
}
