package com.exchangediary.member.service;

import com.exchangediary.global.exception.serviceexception.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"security.jwt.expiration-time=1000"})
public class JwtServiceTest {
    @Autowired
    private JwtService jwtService;

    @Test
    void 토큰_발급_성공() {
        Long memberId = 1L;
        String token = jwtService.generateToken(memberId);

        Long result = jwtService.extractMemberId(token);

        assertThat(result).isEqualTo(memberId);
    }

    @Test
    void 유효한_토큰_검증() {
        Long memberId = 1L;
        String token = jwtService.generateToken(memberId);

        jwtService.verifyToken(token);
    }

    @Test
    void 만료된_토큰_검증() throws InterruptedException {
        Long memberId = 1L;
        String token = jwtService.generateToken(memberId);

        Thread.sleep(1000);
        assertThrows(UnauthorizedException.class, () ->
            jwtService.verifyToken(token)
        );
    }
}
