package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.ui.dto.response.DiaryIdResponse;
import com.exchangediary.diary.ui.dto.response.DiaryMonthlyResponse;
import com.exchangediary.diary.ui.dto.response.DiaryResponse;
import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.InvalidDateException;
import com.exchangediary.global.exception.serviceexception.NotFoundException;
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

    public DiaryResponse viewDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.DIARY_NOT_FOUND,
                        "",
                        String.valueOf(diaryId))
                );
        return DiaryResponse.of(diary);
    }

    public DiaryMonthlyResponse viewMonthlyDiary(int year, int month) {
        checkValidDate(year, month, null);
        List<Diary> diaries = diaryRepository.findByAllYearAndMonth(year, month);
        return DiaryMonthlyResponse.of(year, month, diaries);
    }

    public DiaryIdResponse findDiaryIdByDate(int year, int month, int day) {
        checkValidDate(year, month, day);
        Long diaryId = diaryRepository.findIdByDate(year, month, day)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.DIARY_NOT_FOUND,
                        "",
                        String.format("%d-%02d-%02d", year, month, day))
                );
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
            throw new InvalidDateException(
                    ErrorCode.INVALID_DATE,
                    "",
                    date
            );
        }
    }
}
