package com.exchangediary.diary.ui.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record StickerRequest(
        @NotNull Double coordX,
        @NotNull Double coordY,
        @NotNull @Min(0L) Double width,
        @NotNull @Min(0L) Double height,
        @NotNull @Min(0L) @Max(360L) Double rotation
) {
}
