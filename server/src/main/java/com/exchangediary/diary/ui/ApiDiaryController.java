package com.exchangediary.diary.ui;

import com.exchangediary.diary.service.DiaryCommandService;
import com.exchangediary.diary.service.DiaryQueryService;
import com.exchangediary.diary.ui.dto.request.DiaryRequest;
import com.exchangediary.diary.ui.dto.response.DiaryIdResponse;
import com.exchangediary.diary.ui.dto.response.DiaryMonthlyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping
    public ResponseEntity<Void> createDiary(
            @RequestPart(name = "data") @Valid DiaryRequest diaryRequest,
            @RequestPart(name = "file", required = false) MultipartFile file
    ) {
        Long diaryId = diaryCommandService.createDiary(diaryRequest, file);
        return ResponseEntity
                .created(URI.create(String.format("/api/diary/%d", diaryId)))
                .header("Content-Location", "/diary/" + diaryId)
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
}
