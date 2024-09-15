package com.exchangediary.diary.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StickerRepository extends JpaRepository<Sticker, Long> {
    List<Sticker> findByDiary(Diary diary);
}
