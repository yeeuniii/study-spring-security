package com.exchangediary.diary.ui.dto.response;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.global.util.DateTimeUtil;
import lombok.Builder;

@Builder
public record DiaryDetailResponse(
        Long diaryId,
        String createdAt,
        String content,
        String moodLocation,
        String imageApiPath
        ) {
    public static DiaryDetailResponse of (Diary diary) {
        return DiaryDetailResponse.builder()
                .diaryId(diary.getId())
                .createdAt(DateTimeUtil.KOREAN_DATE_FORMAT.format(diary.getCreatedAt()))
                .content(diary.getContent())
                .moodLocation(diary.getMoodLocation())
                .imageApiPath("/api/diary/upload-image/" + diary.getUploadImage().getId())
                .build();
    }
}
