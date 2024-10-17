package com.exchangediary.diary.ui.dto.response;

import lombok.Builder;

@Builder
public record DiaryWritableResponse(
        Boolean isMyOrder,
        Boolean writtenTodayDiary
) {
    public static DiaryWritableResponse of(Boolean isMyOrder, Boolean writtenTodayDiary) {
        return DiaryWritableResponse.builder()
                .isMyOrder(isMyOrder)
                .writtenTodayDiary(writtenTodayDiary)
                .build();
    }
}
