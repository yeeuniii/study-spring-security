package com.exchangediary.diary.ui;

import com.exchangediary.diary.service.StickerCommandService;
import com.exchangediary.diary.ui.dto.request.StickerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class StickerController {
    private final StickerCommandService stickerCommandService;

    @PostMapping("/diary/{diaryId}/sticker/{stickerId}")
    public ResponseEntity<Void> createSticker(
            @RequestBody StickerRequest stickerRequest,
            @PathVariable Long diaryId,
            @PathVariable Long stickerId
            ) {
        stickerCommandService.createSticker(stickerRequest, diaryId, stickerId);
        return ResponseEntity
                .created(URI.create("/diary/" + diaryId))
                .build();
    }
}
