package com.exchangediary.global.service;

import com.exchangediary.global.domain.StaticImageRepository;
import com.exchangediary.global.domain.entity.StaticImage;
import com.exchangediary.global.domain.entity.StaticImageType;
import com.exchangediary.global.ui.dto.response.StickersResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaticImageQueryService {
    private final StaticImageRepository staticImageRepository;

    public StaticImageQueryService(StaticImageRepository staticImageRepository) {
        this.staticImageRepository = staticImageRepository;
    }

    public StickersResponse findStickers() {
        List<StaticImage> stickers = staticImageRepository.findAllByType(StaticImageType.STICKER);
        return StickersResponse.from(stickers);
    }
}
