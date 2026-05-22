package com.hms.profile.services;

import com.hms.profile.dto.DoctorReqDto;
import com.hms.profile.dto.DoctorRespDto;
import com.hms.profile.dto.DoctorsDropdown;
import com.hms.profile.dto.UpdateDoctorReqDto;

import java.util.List;

public interface IDoctorService {

    Long addDoctor(DoctorReqDto doctor);

    DoctorRespDto getDoctorById(Long id);

    String updateDoctorDetails(UpdateDoctorReqDto updateDoctorReqDto);

    boolean isDoctorExist(long id);

    List<DoctorsDropdown> getDoctorsName();

}
