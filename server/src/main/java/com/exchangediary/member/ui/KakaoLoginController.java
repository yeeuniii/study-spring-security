package com.exchangediary.member.ui;

import com.exchangediary.member.service.KakaoService;
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

    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam String code) {
        String token = kakaoService.getToken(code);
        Long kakaoId = kakaoService.getKakaoUserInfo(token);
        return ResponseEntity.ok(kakaoId);
    }
}
