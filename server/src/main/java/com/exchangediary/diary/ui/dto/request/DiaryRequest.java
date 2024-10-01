package com.exchangediary.diary.ui.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record DiaryRequest (
        @NotEmpty String content,
        @NotEmpty String moodLocation
) {
}
