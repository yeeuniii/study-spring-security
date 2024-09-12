package com.exchangediary.diary.domain.entity;

import com.exchangediary.global.domain.entity.BaseEntity;
import com.exchangediary.global.domain.entity.StaticImage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class Sticker extends BaseEntity {
    @Id
    @Column(name="sticker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Double coordX;
    private Double coordY;
    private Integer coordZ;
    private Double width;
    private Double height;
    private Double rotation;
    @ManyToOne
    @JoinColumn(name = "diary_id")
    private Diary diary;
    @ManyToOne
    @JoinColumn(name = "image_id")
    private StaticImage staticImage;
}
