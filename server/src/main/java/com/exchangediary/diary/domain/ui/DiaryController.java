package com.exchangediary.diary.domain.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {
    @GetMapping("/{diaryId}")
    public ResponseEntity<> viewDatailDiary () {

    }
}
