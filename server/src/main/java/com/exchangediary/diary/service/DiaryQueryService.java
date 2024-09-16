package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.DiaryRepository;
import com.exchangediary.diary.domain.entity.Sticker;
import com.exchangediary.diary.domain.entity.StickerRepository;
import com.exchangediary.diary.domain.entity.UploadImage;
import com.exchangediary.diary.ui.dto.response.DiaryDetailResponse;
import com.exchangediary.diary.ui.dto.response.DiaryDetailStickerResponse;
import com.exchangediary.global.domain.entity.StaticImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryQueryService {
    private final DiaryRepository diaryRepository;
    private final StickerRepository stickerRepository;

    public DiaryDetailResponse viewDetail(Long diaryId) {
        // TODO: exception 핸들러 추가 시 orElseThrow로 수정
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(()-> new NullPointerException());
        List<Sticker> stickers = stickerRepository.findByDiary(diary);

        List<DiaryDetailStickerResponse> stickersResponse = stickers.stream()
                .map(DiaryDetailStickerResponse::from)
                .collect(Collectors.toList());

        String todayMoodUrl = extractTodayMoodImageUrl(diary);
        String imageUrl = extractUploadImageUrl(diary);
        List<DiaryDetailStickerResponse> stickerList = convertStickersToResponse(stickersResponse);

        return DiaryDetailResponse.of(diary, todayMoodUrl, imageUrl, stickerList);
    }

    private static String extractTodayMoodImageUrl(Diary diary) {
        String todayMoodUrl = Optional.ofNullable(diary.getMoodImage()).map(StaticImage::getUrl)
                .orElse(null);
        return todayMoodUrl;
    }

    private static String extractUploadImageUrl(Diary diary) {
        String imageUrl = Optional.ofNullable(diary.getUploadImage()).map(UploadImage::getUrl)
                .orElse(null);
        return imageUrl;
    }

    private static List<DiaryDetailStickerResponse> convertStickersToResponse(List<DiaryDetailStickerResponse> stickersResponse) {
        List<DiaryDetailStickerResponse> stickerList = Optional.ofNullable(stickersResponse)
                .orElse(null);
        return stickerList;
    }
}
