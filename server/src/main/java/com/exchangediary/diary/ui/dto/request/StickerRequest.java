package com.exchangediary.diary.ui.dto.request;

import lombok.Builder;

@Builder
public record StickerRequest(
        double coordX,
        double coordY,
        double width,
        double height,
        double rotation
) {
}
