package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.ui.dto.response.DiaryMonthlyResponse;
import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonthlyQueryService {
    private final DiaryRepository diaryRepository;

    public DiaryMonthlyResponse viewMonthlyDiary(int year, int month) {
        if ((year < 2024 || year > 2100) || (month < 1 || month > 12) ) {
            throw new GlobalException(ErrorCode.INVALID_YEAR_OR_MONTH);
        }
        List<Diary> diaries = diaryRepository.findByYearAndMonth(year, month);
        return DiaryMonthlyResponse.of(year, month, diaries);
    }
}
