package com.exchangediary.diary.ui.dto.response;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.UploadImage;
import lombok.Builder;

import java.time.format.DateTimeFormatter;

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
                .createdAt(diary.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.M.dd")))
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
