package com.exchangediary.diary.ui.dto.response;

import com.exchangediary.diary.domain.entity.Sticker;
import com.exchangediary.global.domain.entity.StaticImage;
import lombok.Builder;

import java.util.Optional;

@Builder
public record DiaryDetailStickerResponse (
        Long stickerId,
        double coordX,
        double coordY,
        int coordZ,
        double width,
        double height,
        double rotation,
        String imageUrl
) {
    public static DiaryDetailStickerResponse from (Sticker sticker) {
        String imageUrl = Optional.ofNullable(sticker.getStaticImage()).map(StaticImage::getUrl)
                .orElse(null);

        return DiaryDetailStickerResponse.builder()
                .stickerId(sticker.getId())
                .coordX(sticker.getCoordX())
                .coordY(sticker.getCoordY())
                .coordZ(sticker.getCoordZ())
                .width(sticker.getWidth())
                .height(sticker.getHeight())
                .rotation(sticker.getRotation())
                .imageUrl(imageUrl)
                .build();
    }
}
