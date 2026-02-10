package com.hms.user.services.impl;

import com.hms.user.dtos.LoginReqDto;
import com.hms.user.dtos.LoginRespDto;
import com.hms.user.dtos.UserDto;
import com.hms.user.entities.User;
import com.hms.user.enums.Roles;
import com.hms.user.exceptions.UserException;
import com.hms.user.repositories.UserRepository;
import com.hms.user.security.CustomUserDetails;
import com.hms.user.services.IUserService;
import com.hms.user.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;
    private UserDetails userDetails;

    @Override
    public String registerUser(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByEmailOrUsername(userDto.getEmail(), userDto.getUsername());
        if (existingUser.isPresent()) {
            throw new UserException(userDto.getUsername(), userDto.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        switch (userDto.getRole()) {
            case "ADMIN":
                user.setRole(Roles.ADMIN);
                break;
            case "DOCTOR":
                user.setRole(Roles.DOCTOR);
                break;
            case "PATIENT":
                user.setRole(Roles.PATIENT);
                break;
            default:
                throw new RuntimeException("Invalid role: " + userDto.getRole());
        }
        userRepository.save(user);
        return "User Register Successfully!!";
    }

    @Override
    public LoginRespDto loginUser(LoginReqDto loginReqDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReqDto.getEmail(), loginReqDto.getPassword()));
        CustomUserDetails customUserDetails = (CustomUserDetails)  userDetailsService.loadUserByUsername(loginReqDto.getEmail());
        LoginRespDto loginRespDto = new LoginRespDto();
        BeanUtils.copyProperties(customUserDetails, loginRespDto);
        loginRespDto.setRole(customUserDetails.getRole().toString());
        String token = jwtUtil.generateToken(customUserDetails);
        loginRespDto.setToken(token);
        return loginRespDto;
    }

    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(id));
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }
}
