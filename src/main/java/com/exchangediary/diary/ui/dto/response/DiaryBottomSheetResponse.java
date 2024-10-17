package com.exchangediary.diary.ui.dto.response;

import lombok.Builder;

@Builder
public record DiaryBottomSheetResponse(
        Boolean isMyOrder,
        Boolean writtenTodayDiary
) {
    public static DiaryBottomSheetResponse of(Boolean isMyOrder, Boolean writtenTodayDiary) {
        return DiaryBottomSheetResponse.builder()
                .isMyOrder(isMyOrder)
                .writtenTodayDiary(writtenTodayDiary)
                .build();
    }
}
