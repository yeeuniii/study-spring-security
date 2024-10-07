package com.exchangediary.member.ui.dto.response;

import lombok.Builder;

@Builder
public record JwtTokenResponse(
        String token
) {
    public static JwtTokenResponse of(String token) {
        return JwtTokenResponse.builder()
                .token(token)
                .build();
    }
}
