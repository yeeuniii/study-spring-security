package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.ui.dto.response.DiaryDetailResponse;
import com.exchangediary.diary.ui.dto.response.DiaryIdResponse;
import com.exchangediary.diary.ui.dto.response.DiaryMonthlyResponse;
import com.exchangediary.global.exception.DiaryNotFoundException;
import com.exchangediary.global.exception.DateRangeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
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
        checkValidDate(year, month, null);
        List<Diary> diaries = diaryRepository.findByAllYearAndMonth(year, month);
        return DiaryMonthlyResponse.of(year, month, diaries);
    }

    public DiaryIdResponse findDiaryIdByDate(int year, int month, int day) {
        checkValidDate(year, month, day);
        Long diaryId = diaryRepository.findIdByDate(year, month, day)
                .orElseThrow(() -> new DiaryNotFoundException(String.format("%d-%02d-%02d", year, month, day)));
        return DiaryIdResponse.builder()
                .diaryId(diaryId)
                .build();
    }

    private void checkValidDate(int year, int month, Integer day) {
        try {
            if (day == null) {
                YearMonth.of(year, month);
            } else {
                LocalDate.of(year, month, day);
            }
        } catch (DateTimeException exception) {
            String date = String.format("%d-%02d", year, month);
            if (day != null) {
                date += String.format("-%02d", day);
            }
            throw new DateRangeException(date);
        }
    }
}
