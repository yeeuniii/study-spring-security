package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.UploadImage;
import com.exchangediary.diary.ui.dto.request.DiaryRequest;
import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.DuplicateException;
import com.exchangediary.global.exception.serviceexception.FailedImageUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryCommandService {
    private final DiaryRepository diaryRepository;
    private final UploadImageService uploadImageService;

    public Diary createDiary(DiaryRequest diaryRequest, MultipartFile file) {
        checkTodayDiaryExistent();

        try {
            UploadImage uploadImage = uploadImageService.saveUploadImage(file);
            Diary diary = Diary.of(diaryRequest, uploadImage);
            return diaryRepository.save(diary);
        } catch (IOException e) {
            throw new FailedImageUploadException(
                    ErrorCode.FAILED_UPLOAD_IMAGE,
                    "",
                    file.getOriginalFilename()
            );
        }
    }

    private void checkTodayDiaryExistent() {
        LocalDate today = LocalDate.now();
        Optional<Long> todayDiary = diaryRepository.findIdByDate(today.getYear(), today.getMonthValue(), today.getDayOfMonth());

        if (todayDiary.isPresent()) {
            throw new DuplicateException(
                    ErrorCode.DIARY_DUPLICATED,
                    "",
                    today.toString()
            );
        }
    }
}
