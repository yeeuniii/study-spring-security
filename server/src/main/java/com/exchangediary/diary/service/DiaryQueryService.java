package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.StickerRepository;
import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.domain.entity.Sticker;
import com.exchangediary.diary.ui.dto.response.DiaryDetailResponse;
import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
