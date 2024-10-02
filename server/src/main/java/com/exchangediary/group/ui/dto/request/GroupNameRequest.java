package com.exchangediary.group.ui.dto.request;

import jakarta.validation.constraints.NotBlank;

public record GroupNameRequest(
        @NotBlank String groupName
) {
}
