package com.hms.user.services;

import com.hms.user.dtos.LoginReqDto;
import com.hms.user.dtos.LoginRespDto;
import com.hms.user.dtos.UserDto;

import java.util.Map;

public interface IUserService {

     String registerUser(UserDto userDto);

     Map<String, String> loginUser(LoginReqDto loginReqDto);

     UserDto getUser(Long id);

     UserDto updateUser(UserDto userDto);
}
