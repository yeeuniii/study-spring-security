package com.exchangediary.diary.ui.dto.response;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.UploadImage;
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
                .imageApiPath(getImageApiPath(diary.getUploadImage()))
                .build();
    }

    private static String getImageApiPath(UploadImage uploadImage) {
        if (uploadImage == null) {
            return null;
        }
        return "/api/diary/upload-image/" + uploadImage.getId();
    }
}
