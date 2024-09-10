package com.exchangediary.diary.domain.entity;

import jakarta.persistence.*;
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
public class Sticker {
    @Id
    @Column(name="sticker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long diaryId;
    private Double coordX;
    private Double coordY;
    private Integer coordZ;
    private Double width;
    private Double height;
    private Double rotation;
    private Long imageId;
}
