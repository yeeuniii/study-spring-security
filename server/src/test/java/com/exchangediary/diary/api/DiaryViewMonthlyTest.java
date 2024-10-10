package com.exchangediary.diary.api;

import com.exchangediary.ApiBaseTest;
import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.diary.ui.dto.response.DiaryMonthlyResponse;
import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class DiaryViewMonthlyTest extends ApiBaseTest {
    private static final String API_PATH = "/api/groups/%d/diaries/monthly";
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private DiaryRepository diaryRepository;

    @Test
    void 달력형_일기_조회() {
        Group group = createGroup();
        groupRepository.save(group);
        Diary diary = createDiary(group);
        diaryRepository.save(diary);

        DiaryMonthlyResponse response = RestAssured
                .given().log().all()
                .queryParam("year", diary.getCreatedAt().getYear())
                .queryParam("month", diary.getCreatedAt().getMonth().getValue())
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .when().get(String.format(API_PATH, group.getId()))
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract().as(DiaryMonthlyResponse.class);

        assertThat(response.year()).isEqualTo(diary.getCreatedAt().getYear());
        assertThat(response.month()).isEqualTo(diary.getCreatedAt().getMonth().getValue());
        assertThat(response.days()).hasSize(1);
    }

    private Group createGroup() {
        return Group.builder()
                .name("버니즈")
                .currentOrder(0)
                .code("code")
                .build();
    }

    private Diary createDiary(Group group) {
        return Diary.builder()
                .content("내용")
                .moodLocation("/images/write-page/emoji/sleepy.svg")
                .group(group)
                .member(member)
                .build();
    }
}