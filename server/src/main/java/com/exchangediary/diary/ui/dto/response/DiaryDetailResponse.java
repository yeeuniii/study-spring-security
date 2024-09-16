package com.exchangediary.diary.ui.dto.response;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.global.util.DateTimeUtil;
import lombok.Builder;

import java.util.List;

@Builder
public record DiaryDetailResponse(
        Long diaryId,
        String createdAt,
        String todayMoodUrl,
        String imageUrl,
        String content,
        List<DiaryDetailStickerResponse> stickers
        ) {
    public static DiaryDetailResponse of (Diary diary, String todayMoodUrl, String imageUrl,
                                          List<DiaryDetailStickerResponse> stickerList) {
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
