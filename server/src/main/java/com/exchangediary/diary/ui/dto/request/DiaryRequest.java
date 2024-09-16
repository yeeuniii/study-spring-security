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
    public Diary toEntity(StaticImage moodImage) { //Todo: 매개변수 uploadImage 추가
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 H:mm:ss");
        LocalDateTime parsedCreatedAt = LocalDateTime.parse(createdAt, formatter);

        return Diary.builder()
                .createdAt(parsedCreatedAt)
                .content(content)
                .moodImage(moodImage)
                //.uploadImage(uploadImage)
                .build();
    }
}