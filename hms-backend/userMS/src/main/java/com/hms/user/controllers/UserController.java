package com.hms.user.controllers;

import com.hms.user.dtos.LoginReqDto;
import com.hms.user.dtos.LoginRespDto;
import com.hms.user.dtos.UserDto;
import com.hms.user.exceptions.HmsException;
import com.hms.user.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/register")
    ResponseEntity<Object> registerUser(@Valid @RequestBody UserDto userDto) {
        String message = userService.registerUser(userDto);
        return ResponseEntity.ok().body(message);
    }

    @PostMapping("/login")
    ResponseEntity<Object> loginUser(@Valid @RequestBody LoginReqDto loginReqDto) throws HmsException {
        try {
            log.info("login user request received by loginReqDto {}", loginReqDto);
            LoginRespDto user = userService.loginUser(loginReqDto);
            return ResponseEntity.ok().body(user);
        } catch (AuthenticationException e) {
            throw new HmsException("INVALID CREDENTIALS");
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getUser(@PathVariable Long id) {
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/test")
    ResponseEntity<Object> test() {
        return ResponseEntity.ok().body("Test");
    }

}
