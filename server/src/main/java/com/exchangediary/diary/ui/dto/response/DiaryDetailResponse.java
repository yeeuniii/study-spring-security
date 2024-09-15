package com.exchangediary.diary.domain.ui.dto.response;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.Sticker;
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
    public static DiaryDetailResponse from (Diary diary, List<Sticker> stickers) {
        List<DiaryDetailStickerResponse> stickersResponse = stickers.stream()
                .map(DiaryDetailStickerResponse::from)
                .collect(Collectors.toList());

        return DiaryDetailResponse.builder()
                .diaryId(diary.getId())
                .createdAt(diary.getCreatedAt().toString())
                .todayMoodUrl(diary.getMoodImage().getUrl())
                .imageUrl(Optional.ofNullable(diary.getUploadImage().getUrl()).orElse(null))
                .content(diary.getContent())
                .stickers(Optional.ofNullable(stickersResponse).orElse(null))
                .build();
    }
}
