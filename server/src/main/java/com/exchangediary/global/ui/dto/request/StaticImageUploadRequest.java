package com.exchangediary.global.ui.dto.request;

import jakarta.validation.constraints.NotNull;

public record StaticImageUploadRequest(
        @NotNull String type
) {
}
