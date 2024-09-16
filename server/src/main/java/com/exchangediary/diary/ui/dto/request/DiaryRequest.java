package com.exchangediary.diary.ui.dto.request;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.UploadImage;
import com.exchangediary.global.domain.entity.StaticImage;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record DiaryRequest (
        String createdAt,
        String content,
        Long todayMoodId
) {
    public Diary toEntity(StaticImage moodImage, UploadImage uploadImage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 H:mm:ss");
        LocalDateTime parsedCreatedAt = LocalDateTime.parse(createdAt, formatter);

        return Diary.builder()
                .createdAt(parsedCreatedAt)
                .content(content)
                .moodImage(moodImage)
                .uploadImage(uploadImage)
                .build();
    }
}