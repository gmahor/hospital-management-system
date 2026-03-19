package com.hms.user;

import com.hms.user.security.CustomUserDetailService;
import com.hms.user.security.CustomUserDetails;
import com.hms.user.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserMsApplicationTests {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void contextLoads() {
//      jwtUtil.isTokenValid("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUEFUSUVOVCIsInByb2ZpbGVJZCI6MiwiaWQiOjMsImVtYWlsIjoiZ291cmF2bWFob3I2MEBnbWFpbC5jb20iLCJ1c2VybmFtZSI6ImdtYWhvciIsImlhdCI6MTc3MzgzNzU4MCwic3ViIjoiZ21haG9yIiwiZXhwIjoxNzczODM3ODgwfQ.YVqCSc1LRm4BwAqtPJ48x_MromaehoBzzPP6KePmSY8");
//      jwtUtil.isTokenValid("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUEFUSUVOVCIsInByb2ZpbGVJZCI6MiwiaWQiOjMsImVtYWlsIjoiZ291cmF2bWFob3I2MEBnbWFpbC5jb20iLCJ1c2VybmFtZSI6ImdtYWhvciIsImlhdCI6MTc3MzkxMjQxMCwic3ViIjoiZ291cmF2bWFob3I2MEBnbWFpbC5jb20iLCJleHAiOjE3NzM5MTMwMTB9.G9iSVyeoeO7v6qJ5KghGonNoL3bpEPyu0Eid8gyXmxI");
        Claims claims = jwtUtil.getClaims("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUEFUSUVOVCIsInByb2ZpbGVJZCI6MiwiaWQiOjMsImVtYWlsIjoiZ291cmF2bWFob3I2MEBnbWFpbC5jb20iLCJ1c2VybmFtZSI6ImdtYWhvciIsImlhdCI6MTc3MzgzNzU4MCwic3ViIjoiZ21haG9yIiwiZXhwIjoxNzczODM3ODgwfQ.YVqCSc1LRm4BwAqtPJ48x_MromaehoBzzPP6KePmSY8");
        System.out.println(claims);
        boolean isTokenExpired = jwtUtil.isTokenExpired("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUEFUSUVOVCIsInByb2ZpbGVJZCI6MiwiaWQiOjMsImVtYWlsIjoiZ291cmF2bWFob3I2MEBnbWFpbC5jb20iLCJ1c2VybmFtZSI6ImdtYWhvciIsImlhdCI6MTc3MzgzNzU4MCwic3ViIjoiZ21haG9yIiwiZXhwIjoxNzczODM3ODgwfQ.YVqCSc1LRm4BwAqtPJ48x_MromaehoBzzPP6KePmSY8");
        System.out.println(isTokenExpired);

    }



}
