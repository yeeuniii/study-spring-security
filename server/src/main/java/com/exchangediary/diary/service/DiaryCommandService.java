package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.UploadImage;
import com.exchangediary.diary.ui.dto.request.DiaryRequest;
import com.exchangediary.global.exception.FailedImageUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryCommandService {
    private final DiaryRepository diaryRepository;
    private final UploadImageService uploadImageService;

    public Diary createDiary(DiaryRequest diaryRequest, MultipartFile file) {
        try {
            UploadImage uploadImage = uploadImageService.saveUploadImage(file);

            Diary diary = Diary.of(diaryRequest, uploadImage);
            return diaryRepository.save(diary);
        } catch (IOException e) {
            throw new FailedImageUploadException(file.getOriginalFilename());
        }
    }
}
