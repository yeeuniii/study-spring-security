package com.exchangediary.group.ui.dto.response;

import lombok.Builder;

@Builder
public record GroupJoinResponse(
        String message
) {
    public static GroupJoinResponse from(String message) {
        return GroupJoinResponse.builder()
                .message(message)
                .build();
    }
}
