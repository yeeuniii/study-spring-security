package com.exchangediary.member.ui;

import com.exchangediary.member.service.JwtService;
import com.exchangediary.member.service.KakaoService;
import com.exchangediary.member.service.MemberRegistrationService;
import com.exchangediary.member.ui.dto.response.JwtTokenResponse;
import com.exchangediary.member.ui.dto.response.MemberIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kakao")
public class KakaoLoginController {
    private final KakaoService kakaoService;
    private final JwtService jwtService;
    private final MemberRegistrationService memberRegistrationService;
 
    @GetMapping("/callback")
    public ResponseEntity<JwtTokenResponse> callback(@RequestParam String code) {
        Long kakaoId = kakaoService.loginKakao(code);
        Long memberId = memberRegistrationService.getOrCreateMember(kakaoId).memberId();
        String token = jwtService.generateToken(memberId);
        JwtTokenResponse response = JwtTokenResponse.of(token);
        return ResponseEntity
                .ok()
                .body(response);
    }
}
