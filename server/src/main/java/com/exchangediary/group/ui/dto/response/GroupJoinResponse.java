package com.exchangediary.group.ui.dto.response;

import lombok.Builder;

@Builder
public record GroupJoinResponse(
        String code
) {
    public static GroupJoinResponse from(String code) {
        return GroupJoinResponse.builder()
                .code(code)
                .build();
    }
}
