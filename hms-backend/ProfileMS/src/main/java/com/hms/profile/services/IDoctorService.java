package com.hms.profile.services;

import com.hms.profile.dto.DoctorReqDto;
import com.hms.profile.dto.DoctorRespDto;
import com.hms.profile.dto.UpdateDoctorReqDto;

public interface IDoctorService {

    Long addDoctor(DoctorReqDto doctor);

    DoctorRespDto getDoctorById(Long id);

    String updateDoctorDetails(UpdateDoctorReqDto updateDoctorReqDto);

}
