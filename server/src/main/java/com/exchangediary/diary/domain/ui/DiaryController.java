package com.exchangediary.diary.domain.ui;

import com.exchangediary.diary.domain.service.DiaryQueryService;
import com.exchangediary.diary.domain.ui.dto.response.DiaryDetailResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {
    private final DiaryQueryService diaryQueryService;

    @GetMapping("/{diaryId}")
    public ResponseEntity<DiaryDetailResponse> viewDetail (@PathVariable("diaryId")Long diaryId) {
        DiaryDetailResponse diaryDetailResponse = diaryQueryService.viewDetail(diaryId);

        return ResponseEntity
                .ok()
                .body(diaryDetailResponse);
    }
}
