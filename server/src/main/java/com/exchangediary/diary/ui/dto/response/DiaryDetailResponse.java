package com.exchangediary.diary.ui.dto.response;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.Sticker;
import com.exchangediary.diary.domain.entity.UploadImage;
import com.exchangediary.global.domain.entity.StaticImage;
import com.exchangediary.global.util.DateTimeUtil;
import lombok.Builder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
public record DiaryDetailResponse(
        Long diaryId,
        String createdAt,
        String todayMoodUrl,
        String imageUrl,
        String content,
        List<DiaryDetailStickerResponse> stickers
        ) {
    public static DiaryDetailResponse of (Diary diary, List<Sticker> stickers) {
        List<DiaryDetailStickerResponse> stickersResponse = stickers.stream()
                .map(DiaryDetailStickerResponse::from)
                .collect(Collectors.toList());

        String todayMoodUrl = Optional.ofNullable(diary.getMoodImage()).map(StaticImage::getUrl)
                .orElse(null);

        String imageUrl = Optional.ofNullable(diary.getUploadImage()).map(UploadImage::getUrl)
                .orElse(null);

        List<DiaryDetailStickerResponse> stickerList = Optional.ofNullable(stickersResponse)
                .orElse(null);

        return DiaryDetailResponse.builder()
                .diaryId(diary.getId())
                .createdAt(DateTimeUtil.KOREAN_DATE_FORMAT.format(diary.getCreatedAt()))
                .todayMoodUrl(todayMoodUrl)
                .imageUrl(imageUrl)
                .content(diary.getContent())
                .stickers(stickerList)
                .build();
    }
}
