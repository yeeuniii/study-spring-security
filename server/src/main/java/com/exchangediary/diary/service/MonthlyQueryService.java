package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.ui.dto.response.DiaryMonthlyResponse;
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
        List<Diary> diaries = diaryRepository.findByYearAndMonth(year, month);
        return DiaryMonthlyResponse.of(year, month, diaries);
    }
}
