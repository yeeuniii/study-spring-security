package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.domain.StickerRepository;
import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.Sticker;
import com.exchangediary.diary.ui.dto.request.StickerRequest;
import com.exchangediary.global.domain.StaticImageRepository;
import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.GlobalException;
import com.exchangediary.global.domain.entity.StaticImage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StickerCommandService {
    private final StickerRepository stickerRepository;
    private final DiaryRepository diaryRepository;
    private final StaticImageRepository staticImageRepository;

    public void createSticker(StickerRequest stickerRequest, long diaryId, long stickerId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new GlobalException(ErrorCode.DIARY_NOT_FOUND));
        StaticImage stickerImage = staticImageRepository.findById(stickerId)
                .orElseThrow(() -> new GlobalException(ErrorCode.STICKER_IMAGE_NOT_FOUND));
        int coordZ = stickerRepository.countByDiaryId(diaryId) + 1;
        Sticker sticker = Sticker.of(stickerRequest, coordZ, diary, stickerImage);
        stickerRepository.save(sticker);
    }
}
