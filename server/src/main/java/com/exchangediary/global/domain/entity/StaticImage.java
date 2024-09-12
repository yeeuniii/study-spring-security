package com.exchangediary.global.domain.entity;

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
@NoArgsConstructor(access = PROTECTED, force = true)
@AllArgsConstructor(access = PRIVATE)
public class StaticImage extends BaseEntity {
    @Id
    @Column(name="image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String url;
    @Enumerated(EnumType.STRING)
    private final StaticImageType type;
}
