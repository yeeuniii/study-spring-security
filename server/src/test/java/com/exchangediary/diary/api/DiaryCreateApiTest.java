package com.exchangediary.diary.api;

import com.exchangediary.ApiBaseTest;
import com.exchangediary.diary.domain.DiaryRepository;
import com.exchangediary.diary.domain.entity.Diary;
import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DiaryCreateApiTest extends ApiBaseTest {
    private static final String API_PATH = "/api/groups/%d/diaries";
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private DiaryRepository diaryRepository;

    @Test
    void 일기_작성_성공() throws JsonProcessingException {
        Group group = createGroup();
        groupRepository.save(group);
        Map<String, String> data = new HashMap<>();
        data.put("content", "내용");
        data.put("moodLocation", "/images/sad.png");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(data);

        Long diaryId = Long.parseLong(
                RestAssured
                .given().log().all()
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .multiPart("data", jsonData, "application/json")
                .multiPart("file", new File("src/test/resources/images/test.jpg"), "image/png")
                .cookie("token", token)
                .when().post(String.format(API_PATH, group.getId()))
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .header("Location")
                .replace("/api/diary/", "")
        );

        Diary newDiary = diaryRepository.findById(diaryId).get();
        assertThat(diaryId).isEqualTo(1L);
        assertThat(newDiary.getGroup().getId()).isEqualTo(group.getId());
        assertThat(newDiary.getMember().getId()).isEqualTo(member.getId());
    }

    private Group createGroup() {
        return Group.builder()
                .name("버니즈")
                .currentOrder(0)
                .code("code")
                .build();
    }
}