package com.exchangediary.global.ui.dto.response;

import com.exchangediary.global.domain.entity.StaticImage;
import lombok.Builder;

import java.util.List;

@Builder
public record MoodsResponse(
        List<StaticImageResponse> moods
) {
    public static MoodsResponse from (List<StaticImage> images) {
        List<StaticImageResponse> moods = images.stream()
                .map(StaticImageResponse::from)
                .toList();
        return MoodsResponse.builder()
                .moods(moods)
                .build();
    }
}
