package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.StickerRepository;
import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.domain.entity.Sticker;
import com.exchangediary.diary.ui.dto.response.DiaryDetailResponse;
import com.exchangediary.diary.ui.dto.response.DiaryMonthlyResponse;
import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.GlobalException;
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
    private final StickerRepository stickerRepository;

    public DiaryDetailResponse viewDetail(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new GlobalException(ErrorCode.DIARY_NOT_FOUND));
        List<Sticker> stickers = stickerRepository.findByDiary(diary);
        return DiaryDetailResponse.of(diary, stickers);
    }

    public DiaryMonthlyResponse viewMonthlyDiary(int year, int month) {
        isValidYearMonth(String.format("%d-%02d", year, month));
        List<Diary> diaries = diaryRepository.findByAllYearAndMonth(year, month);
        return DiaryMonthlyResponse.of(year, month, diaries);
    }

    public static void isValidYearMonth(String yearMonth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        try {
            YearMonth.parse(yearMonth, formatter);
        } catch (DateTimeParseException e) {
            throw new GlobalException(ErrorCode.INVALID_DATE_BAD_REQUEST);
        }
    }

}
