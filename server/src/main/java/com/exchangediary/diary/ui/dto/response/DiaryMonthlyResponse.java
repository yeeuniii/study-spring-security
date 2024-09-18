package com.exchangediary.diary.ui.dto.response;

import com.exchangediary.diary.domain.entity.Diary;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Builder
public record DiaryMonthlyResponse(
        int year,
        int month,
        List<DiaryInfo> days
) {
    public static DiaryMonthlyResponse of(int year, int month, List<Diary> diaries) {
        List<DiaryInfo> diaryInfo = convertDiariesResponse(diaries);
        List<DiaryInfo> dayList = extractdaylist(diaryInfo);
        return DiaryMonthlyResponse.builder()
                .year(year)
                .month(month)
                .days(dayList)
                .build();
    }

    private static List<DiaryInfo> extractdaylist(
            List<DiaryInfo> diaryInfo) {
        List<DiaryInfo> dayList = Optional.ofNullable(diaryInfo)
                .orElse(null);
        return dayList;
    }

    private static List<DiaryInfo> convertDiariesResponse(List<Diary> diaries) {
        return diaries.stream()
                .map(diary -> DiaryInfo.of(diary.getCreatedAt().toLocalDate(), diary.getId()))
                .toList();
    }

    @Builder
    private record DiaryInfo(
            LocalDate date,
            Long diaryId
    ) {
        public static DiaryInfo of(LocalDate date, Long diaryId) {
            return DiaryInfo.builder()
                    .date(date)
                    .diaryId(diaryId)
                    .build();
        }
    }
}
