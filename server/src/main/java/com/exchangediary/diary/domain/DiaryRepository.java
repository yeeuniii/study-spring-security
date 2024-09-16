package com.exchangediary.diary.domain;

import com.exchangediary.diary.domain.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
