package com.exchangediary.diary.ui.dto.response;

import com.exchangediary.diary.domain.entity.Sticker;
import lombok.Builder;

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
        return DiaryDetailStickerResponse.builder()
                .stickerId(sticker.getId())
                .coordX(sticker.getCoordX())
                .coordY(sticker.getCoordY())
                .coordZ(sticker.getCoordZ())
                .width(sticker.getWidth())
                .height(sticker.getHeight())
                .rotation(sticker.getRotation())
                .imageUrl("api/images/upload/" + sticker.getStaticImage().getId())
                .build();
    }
}
