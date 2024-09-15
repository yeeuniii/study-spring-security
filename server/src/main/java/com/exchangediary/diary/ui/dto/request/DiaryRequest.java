package com.exchangediary.diary.ui.dto.request;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.UploadImage;
import com.exchangediary.global.domain.entity.StaticImage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record DiaryRequest (
        String createdAt,
        String content,
        Long todayMoodId
        //String imageUrl
) {
    public Diary toEntity(StaticImage moodImage, UploadImage uploadImage) {
        LocalDateTime parsedCreatedAt = LocalDateTime.parse(createdAt, DateTimeFormatter.ofPattern("yyyy년 M월 d일"));

        return Diary.builder()
                .createdAt(parsedCreatedAt)
                .content(content)
                .moodImage(moodImage)
                //.imageUrl(uploadImage)
                .build();
    }
}