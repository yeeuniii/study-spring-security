package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.entity.Sticker;
import org.springframework.data.repository.CrudRepository;

public interface StickerRepository extends CrudRepository<Sticker, Long> {
}
