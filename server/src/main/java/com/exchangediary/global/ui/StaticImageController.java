package com.exchangediary.global.ui;

import com.exchangediary.global.service.StaticImageQueryService;
import com.exchangediary.global.ui.dto.response.StickersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StaticImageController {
    private final StaticImageQueryService staticImageQueryService;

    @GetMapping("stickers")
    public ResponseEntity<StickersResponse> findStickers() {
        StickersResponse stickers = staticImageQueryService.findStickers();
        return ResponseEntity
                .ok()
                .body(stickers);
    }
}
