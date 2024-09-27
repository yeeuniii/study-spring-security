package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.ui.dto.response.DiaryDetailResponse;
import com.exchangediary.diary.ui.dto.response.DiaryIdResponse;
import com.exchangediary.diary.ui.dto.response.DiaryMonthlyResponse;
import com.exchangediary.global.exception.DiaryNotFoundException;
import com.exchangediary.global.exception.InvalidDateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryQueryService {
    private final DiaryRepository diaryRepository;

    public DiaryDetailResponse viewDetail(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new DiaryNotFoundException(String.valueOf(diaryId)));
        return DiaryDetailResponse.of(diary);
    }

    public DiaryMonthlyResponse viewMonthlyDiary(int year, int month) {
        isValidYearMonth(String.format("%d-%02d", year, month));
        List<Diary> diaries = diaryRepository.findByAllYearAndMonth(year, month);
        return DiaryMonthlyResponse.of(year, month, diaries);
    }

    private void isValidYearMonth(String yearMonth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        try {
            YearMonth.parse(yearMonth, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(yearMonth);
        }
    }

    public DiaryIdResponse findDiaryIdByDate(int year, int month, int day) {
        Long diaryId = diaryRepository.findIdByDate(year, month, day)
                .orElseThrow(() -> new InvalidDateException(String.format("%d-%02d-%02d", year, month, day)));
        return DiaryIdResponse.builder()
                .diaryId(diaryId)
                .build();
    }
}
