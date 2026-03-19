package com.hms.user.controllers;

import com.hms.user.constants.MessageConstant;
import com.hms.user.dtos.LoginReqDto;
import com.hms.user.dtos.UserDto;
import com.hms.user.exceptions.HmsException;
import com.hms.user.exceptions.UserException;
import com.hms.user.services.IUserService;
import com.hms.user.utils.JwtUtil;
import com.hms.user.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    private final JwtUtil jwtUtil;

    private final ResponseHandler responseHandler;

    @PostMapping("/register")
    ResponseEntity<Object> registerUser(@Valid @RequestBody UserDto userDto) {
        try {
            String message = userService.registerUser(userDto);
            return responseHandler.response(message, MessageConstant.USER_REGISTER_SUCCESS, true, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return responseHandler.response("", MessageConstant.ERROR_WHILE_REGISTER_USER, false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    ResponseEntity<Object> loginUser(@Valid @RequestBody LoginReqDto loginReqDto) throws HmsException {
        try {
            log.info("login user request received by loginReqDto {}", loginReqDto);
            Map<String, String> data = userService.loginUser(loginReqDto);
            return responseHandler.response(data, MessageConstant.USER_LOGIN_SUCCESS, true, HttpStatus.OK);
        } catch (BadCredentialsException be) {
            log.error(be.getMessage());
            return responseHandler.response("", MessageConstant.INVALID_CREDENTIAL, false, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return responseHandler.response("", MessageConstant.ERROR_WHILE_LOGIN, false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getUser(@PathVariable Long id) {
        try {
            UserDto user = userService.getUser(id);
            return responseHandler.response("", MessageConstant.USER_FETCHED_SUCCESS, false, HttpStatus.NOT_FOUND);
        } catch (UserException ue) {
            log.error(ue.getMessage());
            return responseHandler.response("", MessageConstant.USER_NOT_FOUND, false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return responseHandler.response("", MessageConstant.ERROR_WHILE_GETTING_USER, false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/refreshToken")
    ResponseEntity<Object> refreshToken(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return responseHandler.response(
                        "",
                        "Invalid Authorization header",
                        false,
                        HttpStatus.BAD_REQUEST
                );
            }
            String token = authHeader.substring(7);
            if (token.trim().isEmpty()) {
                return responseHandler.response(
                        "",
                        "Token is required",
                        false,
                        HttpStatus.BAD_REQUEST
                );
            }
            boolean isTokenExpired = jwtUtil.isTokenExpired(token);
            if (isTokenExpired) {
                return responseHandler.response("", MessageConstant.TOKEN_EXPIRED, false, HttpStatus.UNAUTHORIZED);
            }
            boolean isTokenValid = jwtUtil.isTokenValid(token);
            if (!isTokenValid) {
                return responseHandler.response("", MessageConstant.TOKEN_NOT_VALID, false, HttpStatus.UNAUTHORIZED);
            }
            String accessToken = jwtUtil.generateAccessTokenWithRefreshToken(token);
            return responseHandler.response(accessToken, MessageConstant.NEW_TOKEN_GENERATED, true, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return responseHandler.response("", MessageConstant.ERROR_WHILE_GENERATING_TOKEN, false, HttpStatus.BAD_REQUEST);
    }

}
