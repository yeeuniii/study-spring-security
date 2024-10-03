package com.exchangediary.group.ui.dto.response;

import lombok.Builder;

@Builder
public record GroupNicknameResponse(
        boolean verification
) {
    public static GroupNicknameResponse from(boolean verification) {
        return GroupNicknameResponse.builder()
                .verification(verification)
                .build();
    }
}
