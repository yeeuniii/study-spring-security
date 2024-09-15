package com.exchangediary.global.ui.dto.response;

import com.exchangediary.global.domain.entity.StaticImage;
import lombok.Builder;

import java.util.List;

@Builder
public record StickersResponse(
        List<StaticImageResponse> stickers
) {
    public static StickersResponse from(List<StaticImage> staticImages) {
        List<StaticImageResponse> stickers = staticImages.stream()
                .map(StaticImageResponse::from)
                .toList();
        return StickersResponse.builder()
                .stickers(stickers)
                .build();
    }
}
