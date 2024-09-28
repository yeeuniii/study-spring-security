package com.exchangediary.member.ui;

import com.exchangediary.member.service.KakaoService;
import com.exchangediary.member.service.MemberRegistrationService;
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

    private final MemberRegistrationService memberRegistrationService;
 
    @GetMapping("/callback")
    public ResponseEntity<MemberIdResponse> callback(@RequestParam String code) {
        Long kakaoId = kakaoService.loginKakao(code);
        MemberIdResponse memberIdResponse = memberRegistrationService.getOrCreateMember(kakaoId);
        return ResponseEntity
                .ok()
                .body(memberIdResponse);
    }
}
