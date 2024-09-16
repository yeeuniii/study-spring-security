package com.exchangediary.diary.ui.dto.response;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.Sticker;
import com.exchangediary.diary.domain.entity.UploadImage;
import com.exchangediary.global.domain.entity.StaticImage;
import com.exchangediary.global.util.DateTimeUtil;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Builder
@RequiredArgsConstructor
public record DiaryDetailResponse(
        Long diaryId,
        String createdAt,
        String todayMoodUrl,
        String imageUrl,
        String content,
        List<DiaryDetailStickerResponse> stickers
        ) {
    public static DiaryDetailResponse of (Diary diary, List<Sticker> stickers) {
        List<DiaryDetailStickerResponse> stickersResponse = convertStickersResponse(stickers);

        String todayMoodUrl = extractTodayMoodImageUrl(diary);

        String imageUrl = extractImageUrl(diary);

        List<DiaryDetailStickerResponse> stickerList = extractStickers(stickersResponse);

        return DiaryDetailResponse.builder()
                .diaryId(diary.getId())
                .createdAt(DateTimeUtil.KOREAN_DATE_FORMAT.format(diary.getCreatedAt()))
                .todayMoodUrl(todayMoodUrl)
                .imageUrl(imageUrl)
                .content(diary.getContent())
                .stickers(stickerList)
                .build();
    }

    private static List<DiaryDetailStickerResponse> extractStickers(
            List<DiaryDetailStickerResponse> stickersResponse) {
        List<DiaryDetailStickerResponse> stickerList = Optional.ofNullable(stickersResponse)
                .orElse(null);
        return stickerList;
    }

    private static String extractImageUrl(Diary diary) {
        String imageUrl = Optional.ofNullable(diary.getUploadImage()).map(UploadImage::getUrl)
                .orElse(null);
        return imageUrl;
    }

    private static String extractTodayMoodImageUrl(Diary diary) {
        String todayMoodUrl = Optional.ofNullable(diary.getMoodImage()).map(StaticImage::getUrl)
                .orElse(null);
        return todayMoodUrl;
    }

    private static List<DiaryDetailStickerResponse> convertStickersResponse(List<Sticker> stickers) {
        List<DiaryDetailStickerResponse> stickersResponse = stickers.stream()
                .map(DiaryDetailStickerResponse::from)
                .toList();
        return stickersResponse;
    }
}
