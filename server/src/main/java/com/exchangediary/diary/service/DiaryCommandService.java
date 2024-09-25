package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.UploadImage;
import com.exchangediary.diary.ui.dto.request.DiaryRequest;
import com.exchangediary.diary.ui.dto.request.UploadImageRequest;
import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.GlobalException;
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
    private final UploadImageService imageService;

    public Diary createDiary(DiaryRequest diaryRequest, UploadImageRequest uploadImageRequest) {
        UploadImage uploadImage = null;
        MultipartFile file = uploadImageRequest.getFile();

        if (file != null && !file.isEmpty()) {
            try {
                uploadImage = imageService.saveUploadImage(file);
            } catch (IOException e) {
                throw new GlobalException(ErrorCode.IMAGE_UPLOAD_ERROR);
            }
        }
        Diary diary = Diary.of(diaryRequest, uploadImage);
        return diaryRepository.save(diary);
    }
}
