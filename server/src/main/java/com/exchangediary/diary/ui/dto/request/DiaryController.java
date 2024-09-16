package com.exchangediary.diary.ui.dto.request;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.service.DiaryCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {
    private final DiaryCommandService diaryCommandService;

    @PostMapping
    public ResponseEntity<Long> createDiary(
            @RequestPart(name = "data") DiaryRequest diaryRequest,
            @RequestPart(name = "file", required = false) MultipartFile file) {

        UploadImageRequest uploadImageRequest = new UploadImageRequest();
        uploadImageRequest.setFile(file);

        Diary diary = diaryCommandService.createDiary(diaryRequest, uploadImageRequest);
        return ResponseEntity.status(201).body(diary.getId());
    }
}
