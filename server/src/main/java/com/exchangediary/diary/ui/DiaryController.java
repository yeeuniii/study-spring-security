package com.exchangediary.diary.ui;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.service.DiaryCommandService;
import com.exchangediary.diary.ui.dto.request.DiaryRequest;
import com.exchangediary.diary.ui.dto.request.UploadImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

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
        return ResponseEntity.created(URI.create(String.format("/diary/%d/", diary.getId()))).body(diary.getId());
    }
}
