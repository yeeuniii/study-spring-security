package com.exchangediary.member;

import com.exchangediary.member.domain.MemberRepository;
import com.exchangediary.member.service.KakaoService;
import com.exchangediary.member.service.MemberRegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class KakaoLoginTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    KakaoService kakaoService;
    @Autowired
    MemberRegistrationService memberRegistrationService;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void setup() {
        memberRepository.deleteAll();
    }

    @Test
    void 카카오_로그인_성공() throws Exception {
        Long kakaoId = 1L;
        String code = "randomCode";

        when(kakaoService.loginKakao(any(String.class))).thenReturn(kakaoId);

        mockMvc.perform(get("/api/kakao/callback")
                        .param("code", code))
                .andExpect(status().isOk());
    }
}