package com.exchangediary.global.domain;

import com.exchangediary.global.domain.entity.StaticImage;
import com.exchangediary.global.domain.entity.StaticImageType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StaticImageRepository extends CrudRepository<StaticImage, Long> {
    List<StaticImage> findAllByType(StaticImageType type);
}
