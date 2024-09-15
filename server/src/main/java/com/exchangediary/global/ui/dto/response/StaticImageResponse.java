package com.exchangediary.global.ui.dto.response;

import com.exchangediary.global.domain.entity.StaticImage;
import lombok.Builder;

@Builder
public record StaticImageResponse(
        long id,
        String imageUrl
) {
    public static StaticImageResponse from(StaticImage staticImage) {
        return StaticImageResponse.builder()
                .id(staticImage.getId())
                .imageUrl(staticImage.getUrl())
                .build();
    }
}
