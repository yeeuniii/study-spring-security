package com.exchangediary.diary.ui.dto.request;

import jakarta.validation.constraints.NotNull;

public record DiaryRequest (
        @NotNull String content,
        @NotNull Long todayMoodId
) {
}
