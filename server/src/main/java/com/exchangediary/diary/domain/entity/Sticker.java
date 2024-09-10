package com.exchangediary.diary.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class Sticker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stickerId;
    private Long diaryId;
    private Long groupId;
    private Double coordX;
    private Double coordY;
    private BigInteger coordZ;
    private Double width;
    private Double height;
    private Double rotation;
    private Long imageId;
}
