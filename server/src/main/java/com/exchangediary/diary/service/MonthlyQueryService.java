package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.ui.dto.response.DiaryMonthlyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonthlyQueryService {
    private final DiaryRepository diaryRepository;

    public DiaryMonthlyResponse getMonthlyDiary(int year, int month) {
        List<Diary> diaries = diaryRepository.findDiariesByYearAndMonth(year, month);

        List<DiaryMonthlyResponse.DiaryInfo> dayList = diaries.stream()
                .map(diary -> new DiaryMonthlyResponse.DiaryInfo(diary.getCreatedAt().toLocalDate(), diary.getId()))
                .collect(Collectors.toList());

        return new DiaryMonthlyResponse(year, month, dayList);
    }
}
