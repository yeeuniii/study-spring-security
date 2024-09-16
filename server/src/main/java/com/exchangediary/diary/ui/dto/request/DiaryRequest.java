package com.exchangediary.diary.ui.dto.request;

public record DiaryRequest (
        String content,
        Long todayMoodId
) {
}
