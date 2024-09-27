package com.exchangediary.member.service;

import com.exchangediary.member.ui.dto.request.KakaoTokenRequest;
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
    @Value("${kakao.client_id}")
    private String client_id;
    @Value("${kakao.redirect_uri}")
    private String redirect_uri;


    public String getToken(String code) {
        KakaoTokenRequest kakaoTokenRequest = KakaoTokenRequest.from(client_id, redirect_uri, code);
        System.out.println(kakaoTokenRequest);
        KakaoTokenResponse kakaoTokenResponse = requestToken(kakaoTokenRequest);
        return kakaoTokenResponse.access_token();
    }

    private KakaoTokenResponse requestToken(KakaoTokenRequest kakaoTokenRequest) {
        RestClient restClient = RestClient.create();
        return restClient.post()
                .uri(KAKAO_TOKEN_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(kakaoTokenRequest.convertMap())
                .retrieve()
                .body(KakaoTokenResponse.class);
    }
}
