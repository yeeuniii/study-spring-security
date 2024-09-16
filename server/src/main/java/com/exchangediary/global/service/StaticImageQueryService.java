package com.exchangediary.global.service;

import com.exchangediary.global.domain.StaticImageRepository;
import com.exchangediary.global.domain.entity.StaticImage;
import com.exchangediary.global.domain.entity.StaticImageType;
import com.exchangediary.global.ui.dto.response.MoodsResponse;
import com.exchangediary.global.ui.dto.response.StickersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StaticImageQueryService {
    private final StaticImageRepository staticImageRepository;

    public StickersResponse findStickers() {
        List<StaticImage> stickers = staticImageRepository.findAllByType(StaticImageType.STICKER);
        return StickersResponse.from(stickers);
    }

    public MoodsResponse findMoods() {
        List<StaticImage> moods = staticImageRepository.findAllByType(StaticImageType.MOOD);
        return MoodsResponse.from(moods);
    }
}
