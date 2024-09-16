package com.exchangediary.global.ui;

import com.exchangediary.diary.domain.entity.UploadImage;
import com.exchangediary.global.domain.entity.StaticImage;
import com.exchangediary.global.service.ImageService;
import com.exchangediary.global.service.StaticImageQueryService;
import com.exchangediary.global.ui.dto.response.MoodsResponse;
import com.exchangediary.global.ui.dto.response.StickersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class StaticImageController {
    private final StaticImageQueryService staticImageQueryService;
    private final ImageService imageService;

    @GetMapping("stickers")
    public ResponseEntity<StickersResponse> findStickers() {
        StickersResponse stickers = staticImageQueryService.findStickers();
        return ResponseEntity
                .ok()
                .body(stickers);
    }

    @GetMapping("moods")
    public ResponseEntity<MoodsResponse> findMoods() {
        MoodsResponse moods = staticImageQueryService.findMoods();
        return ResponseEntity
                .ok()
                .body(moods);
    }

    @GetMapping("api/images/upload/{id}")
    public ResponseEntity<byte[]> getUploadImage(@PathVariable Long id) {
        Optional<UploadImage> imageOptional = imageService.getUploadImage(id);

        if (imageOptional.isPresent()) {
            UploadImage image = imageOptional.get();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(image.getContentType()));

            return new ResponseEntity<>(image.getImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/images/static/{id}")
    public ResponseEntity<byte[]> getStaticImage(@PathVariable Long id) {
        Optional<StaticImage> imageOptional = imageService.getStaticImage(id);

        if (imageOptional.isPresent()) {
            StaticImage image = imageOptional.get();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(image.getContentType()));

            return new ResponseEntity<>(image.getImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
