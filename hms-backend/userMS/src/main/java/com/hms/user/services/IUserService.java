package com.hms.user.services;

import com.hms.user.dtos.LoginReqDto;
import com.hms.user.dtos.LoginRespDto;
import com.hms.user.dtos.UserDto;

public interface IUserService {

     String registerUser(UserDto userDto);

     LoginRespDto loginUser(LoginReqDto loginReqDto);

     UserDto getUser(Long id);

     UserDto updateUser(UserDto userDto);
}
