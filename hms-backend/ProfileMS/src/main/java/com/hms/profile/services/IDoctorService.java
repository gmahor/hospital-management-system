package com.hms.profile.services;

import com.hms.profile.dto.DoctorReqDto;
import com.hms.profile.dto.DoctorRespDto;

public interface IDoctorService {

    Long addDoctor(DoctorReqDto doctor);

    DoctorRespDto getDoctorById(Long id);

}
