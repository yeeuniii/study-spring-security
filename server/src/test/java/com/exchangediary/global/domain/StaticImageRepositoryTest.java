package com.exchangediary.global.domain;


import com.exchangediary.global.domain.entity.StaticImage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class StaticImageRepositoryTest {
    @Autowired
    private StaticImageRepository staticImageRepository;

    @Test
    public void 단건_기분_이미지_저장됨 () {
        StaticImage image = StaticImage.builder()
                .image()
                .type()
                .url()
                .contentType()
                .filename()
                .build();

        StaticImage saveImage = staticImageRepository.save(image);
        Assertions.assertThat(saveImage).isEqualTo(image);
    }


}