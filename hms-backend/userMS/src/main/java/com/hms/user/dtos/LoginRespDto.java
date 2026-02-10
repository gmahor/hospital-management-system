package com.hms.user.dtos;

import lombok.Data;

@Data
public class LoginRespDto {

    private String username;

    private String email;

    private String role;

    private String token;
}
