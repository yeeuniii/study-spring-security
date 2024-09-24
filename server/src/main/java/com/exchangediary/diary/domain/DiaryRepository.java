package com.exchangediary.diary.domain;

import com.exchangediary.diary.domain.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @Query("SELECT d FROM Diary d WHERE YEAR(d.createdAt) = :year AND MONTH(d.createdAt) = :month")
    List<Diary> findByAllYearAndMonth(int year, int month);
    @Query("SELECT d.id FROM Diary d WHERE YEAR(d.createdAt) = :year AND MONTH(d.createdAt) = :month AND DAY(d.createdAt) = :day")
    Optional<Long> findIdByDate(int year, int month, int day);
}
