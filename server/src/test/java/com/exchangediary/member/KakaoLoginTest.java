package com.exchangediary.member;

import com.exchangediary.member.domain.MemberRepository;
import com.exchangediary.member.domain.entity.Member;
import com.exchangediary.member.service.KakaoService;
import com.exchangediary.member.ui.dto.response.MemberIdResponse;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KakaoLoginTest {
    @LocalServerPort
    private int port;
    @MockBean
    private KakaoService kakaoService;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        memberRepository.deleteAllInBatch();
    }

    @Test
    void 새로운_회원_카카오_로그인_성공() {
        String mockCode = "randomCode";
        Long mockKakaoId = 1L;

        when(kakaoService.loginKakao(any(String.class))).thenReturn(mockKakaoId);

        RestAssured
                .given().log().all()
                .when().get("/api/kakao/callback?code="+mockCode)
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void 기존_회원_카카오_로그인_성공() {
        String mockCode = "randomCode";
        Long mockKakaoId = 1L;
        Member member = Member.builder().kakaoId(mockKakaoId).build();
        memberRepository.save(member);

        when(kakaoService.loginKakao(any(String.class))).thenReturn(mockKakaoId);

        MemberIdResponse response = RestAssured
                .given().log().all()
                .when().get("/api/kakao/callback?code="+mockCode)
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract().as(MemberIdResponse.class);

        assertThat(response.memberId()).isEqualTo(member.getId());
    }
}
