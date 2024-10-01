package com.exchangediary.diary.ui;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.UploadImage;
import com.exchangediary.diary.service.DiaryCommandService;
import com.exchangediary.diary.service.DiaryQueryService;
import com.exchangediary.diary.service.UploadImageService;
import com.exchangediary.diary.ui.dto.request.DiaryRequest;
import com.exchangediary.diary.ui.dto.response.DiaryDetailResponse;
import com.exchangediary.diary.ui.dto.response.DiaryIdResponse;
import com.exchangediary.diary.ui.dto.response.DiaryMonthlyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class ApiDiaryController {
    private final DiaryCommandService diaryCommandService;
    private final DiaryQueryService diaryQueryService;
    private final UploadImageService uploadImageService;

    @PostMapping
    public ResponseEntity<Void> createDiary(
            @RequestPart(name = "data") @Valid DiaryRequest diaryRequest,
            @RequestPart(name = "file", required = false) MultipartFile file
    ) {
        Diary diary = diaryCommandService.createDiary(diaryRequest, file);
        return ResponseEntity
                .created(URI.create(String.format("/api/diary/%d", diary.getId())))
                .header("Content-Location", "/diary/" + diary.getId())
                .build();
    }

    @GetMapping
    public ResponseEntity<DiaryIdResponse> findDiaryId(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int day
    ) {
        DiaryIdResponse diaryIdResponse = diaryQueryService.findDiaryIdByDate(year, month, day);
        return ResponseEntity
                .ok()
                .body(diaryIdResponse);
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<DiaryDetailResponse> viewDetail(@PathVariable Long diaryId) {
        DiaryDetailResponse diaryDetailResponse = diaryQueryService.viewDetail(diaryId);
        return ResponseEntity
                .ok()
                .body(diaryDetailResponse);
    }

    @GetMapping("/monthly")
    public ResponseEntity<DiaryMonthlyResponse> viewMonthlyDiary(
            @RequestParam int year,
            @RequestParam int month
    ) {
        DiaryMonthlyResponse diaryMonthlyResponse = diaryQueryService.viewMonthlyDiary(year, month);
        return ResponseEntity
                .ok()
                .body(diaryMonthlyResponse);
    }

    @GetMapping("/upload-image/{imageId}")
    public ResponseEntity<byte[]> getUploadImage(@PathVariable Long imageId) {
        UploadImage image = uploadImageService.getUploadImage(imageId);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .body(image.getImage());
   }
}
