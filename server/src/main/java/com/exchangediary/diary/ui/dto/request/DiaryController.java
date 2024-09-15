package com.exchangediary.diary.ui.dto.request;

import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.service.DiaryCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {
    private final DiaryCommandService diaryCommandService;

    @PostMapping
    public ResponseEntity<Long> createDiary(@RequestBody DiaryRequest diaryRequest) {
        Diary diary = diaryCommandService.createDiary(diaryRequest);
        return ResponseEntity.status(201).body(diary.getId());
    }
}
