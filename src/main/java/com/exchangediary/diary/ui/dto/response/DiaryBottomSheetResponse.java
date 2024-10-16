package com.exchangediary.diary.ui.dto.response;

import lombok.Builder;

@Builder
public record DiaryBottomSheetResponse(
        Boolean myOrder,
        Boolean todayDiary
) {
    public static DiaryBottomSheetResponse of(Boolean myOrder, Boolean todayDiary) {
        return DiaryBottomSheetResponse.builder()
                .myOrder(myOrder)
                .todayDiary(todayDiary)
                .build();
    }
}
