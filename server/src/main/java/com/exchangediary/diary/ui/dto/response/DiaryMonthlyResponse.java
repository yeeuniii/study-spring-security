package com.exchangediary.diary.ui.dto.response;

import com.exchangediary.diary.domain.entity.Diary;
import lombok.Builder;

import java.util.List;

@Builder
public record DiaryMonthlyResponse(
        int year,
        int month,
        List<DiaryInfo> days
) {
    public static DiaryMonthlyResponse of(int year, int month, List<Diary> diaries) {
        List<DiaryInfo> diaryInfo = diaries.stream()
                .map(diary -> DiaryInfo.of(diary.getCreatedAt().getDayOfMonth()))
                .toList();
        return DiaryMonthlyResponse.builder()
                .year(year)
                .month(month)
                .days(diaryInfo)
                .build();
    }

    @Builder
    private record DiaryInfo(
            int date
    ) {
        public static DiaryInfo of(int date) {
            return DiaryInfo.builder()
                    .date(date)
                    .build();
        }
    }
}
