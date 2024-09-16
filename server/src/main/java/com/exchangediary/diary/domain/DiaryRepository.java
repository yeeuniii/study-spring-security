package com.exchangediary.diary.domain;

import com.exchangediary.diary.domain.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findDiariesByYearAndMonth(@Param("year") int year, @Param("month") int month);
}
