package com.exchangediary.diary.ui;

import com.exchangediary.diary.service.StickerCommandService;
import com.exchangediary.diary.ui.dto.request.StickerRequest;
import jakarta.validation.Valid;
import com.exchangediary.diary.service.DiaryQueryService;
import com.exchangediary.diary.ui.dto.response.DiaryDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {
    private final StickerCommandService diaryCommandService;
    private final DiaryQueryService diaryQueryService;

    @GetMapping("/{diaryId}")
    public ResponseEntity<DiaryDetailResponse> viewDetail (@PathVariable Long diaryId) {
        DiaryDetailResponse diaryDetailResponse = diaryQueryService.viewDetail(diaryId);

        return ResponseEntity
                .ok()
                .body(diaryDetailResponse);
    }

    @PostMapping("/{diaryId}/sticker/{stickerId}")
    public ResponseEntity<Void> createSticker(
            @RequestBody @Valid StickerRequest stickerRequest,
            @PathVariable Long diaryId,
            @PathVariable Long stickerId
    ) {
        diaryCommandService.createSticker(stickerRequest, diaryId, stickerId);
        return ResponseEntity
                .created(URI.create("/diary/" + diaryId))
                .build();
    }
}
