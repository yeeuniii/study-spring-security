package com.exchangediary.diary.ui.dto.response;

import java.time.LocalDate;
import java.util.List;

public record DiaryMonthlyResponse(
        int year,
        int month,
        List<DiaryInfo> day
) {
    public record DiaryInfo(LocalDate date, Long diaryId) {
    }
}
