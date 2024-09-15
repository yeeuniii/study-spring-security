package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.UploadImageRepository;
import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.domain.entity.DiaryRepository;
import com.exchangediary.diary.domain.entity.UploadImage;
import com.exchangediary.diary.ui.dto.request.DiaryRequest;
import com.exchangediary.global.domain.StaticImageRepository;
import com.exchangediary.global.domain.entity.StaticImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiaryCommandService {
    private final DiaryRepository diaryRepository;
    private final StaticImageRepository staticImageRepository;
    private final UploadImageRepository uploadImageRepository;

    public Diary createDiary(DiaryRequest diaryRequest) {
        StaticImage moodImage =
                staticImageRepository.findById(diaryRequest.todayMoodId()).orElse(null);
//        UploadImage uploadImage =
//                uploadImageRepository.findByUrl(diaryRequest.imageUrl()).orElse(null);
//        Diary diary = diaryRequest.toEntity(moodImage, uploadImage);
        Diary diary = diaryRequest.toEntity(moodImage); //Todo: uploadImage 값 보내줘야 함
        return diaryRepository.save(diary);
    }
}
