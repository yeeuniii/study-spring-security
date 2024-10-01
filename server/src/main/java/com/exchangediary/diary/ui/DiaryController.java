package com.exchangediary.diary.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diary")
public class DiaryController {
    @GetMapping
    public String writePage() {
        return "write-page";
    }

    @GetMapping("/{diaryId}")
    public String viewDiary(Model model, @PathVariable Long diaryId) {
        model.addAttribute("diaryId", diaryId);
        return "view-diary";
    }
}
