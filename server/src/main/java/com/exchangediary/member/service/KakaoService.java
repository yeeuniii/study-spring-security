package com.exchangediary.member.service;

import com.exchangediary.member.ui.dto.request.KakaoTokenRequest;
import com.exchangediary.member.ui.dto.response.KakaoIdResponse;
import com.exchangediary.member.ui.dto.response.KakaoTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

@Service
@Transactional
public class KakaoService {
    private final static String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private final static String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private final RestClient restClient = RestClient.create();
    @Value("${kakao.client_id}")
    private String client_id;
    @Value("${kakao.redirect_uri}")
    private String redirect_uri;

    public String getToken(String code) {
        KakaoTokenRequest kakaoTokenRequest = KakaoTokenRequest.from(client_id + "123", redirect_uri, code);
        System.out.println(kakaoTokenRequest);
        KakaoTokenResponse kakaoTokenResponse = requestToken(kakaoTokenRequest);
        return kakaoTokenResponse.access_token();
    }

    private KakaoTokenResponse requestToken(KakaoTokenRequest kakaoTokenRequest) {
        return restClient.post()
                .uri(KAKAO_TOKEN_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(kakaoTokenRequest.convertMap())
                .retrieve()
                .body(KakaoTokenResponse.class);
    }

    public Long getKakaoUserInfo(String token) {
        return restClient.get()
                .uri(KAKAO_USER_INFO_URL)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(KakaoIdResponse.class)
                .id();
    }
}
