package com.exchangediary.diary.domain.entity;

import com.exchangediary.diary.ui.dto.request.DiaryRequest;
import com.exchangediary.global.domain.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.LongVarcharJdbcType;

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
    @Lob
    @Column(columnDefinition="text")
    @JdbcType(LongVarcharJdbcType.class)
    @NotNull
    private String content;
    @NotNull
    private String moodLocation;
    @OneToOne(mappedBy = "diary")
    private UploadImage uploadImage;

    public static Diary of(DiaryRequest diaryRequest, UploadImage uploadImage) {
        return Diary.builder()
                .content(diaryRequest.content())
                .moodLocation(diaryRequest.moodLocation())
                .uploadImage(uploadImage)
                .build();
    }
}
