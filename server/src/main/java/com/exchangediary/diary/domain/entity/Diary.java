package com.exchangediary.diary.domain.entity;

import com.exchangediary.diary.ui.dto.request.DiaryRequest;
import com.exchangediary.global.domain.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED, force = true)
@AllArgsConstructor(access = PRIVATE)
public class Diary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;
    private String content;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upload_image_id")
    private UploadImage uploadImage;
    private String moodLocation;

    public static Diary of(DiaryRequest diaryRequest, UploadImage uploadImage) {
        return Diary.builder()
                .content(diaryRequest.content())
                .moodLocation(diaryRequest.moodLocation())
                .uploadImage(uploadImage)
                .build();
    }
}
