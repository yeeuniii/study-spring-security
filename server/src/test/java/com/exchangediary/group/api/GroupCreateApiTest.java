package com.exchangediary.group.api;

import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.ui.dto.request.GroupNameRequest;
import com.exchangediary.group.ui.dto.response.GroupIdResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:truncate.sql"}, executionPhase = BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class GroupCreateApiTest {
    private static final String API_PATH = "/api/groups";
    @LocalServerPort
    private int port;
    @Autowired
    private GroupRepository groupRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 그룹_생성_성공() {
        String groupName = "버니즈";

        Long groupId = RestAssured
                .given().log().all()
                .body(new GroupNameRequest(groupName))
                .contentType(ContentType.JSON)
                .when().post(API_PATH)
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(GroupIdResponse.class)
                .groupId();

        // ToDo: 아래 부분 location으로 확인하도록 수정
        String result = groupRepository.findById(groupId).get().getName();
        assertThat(result).isEqualTo(groupName);
    }
}
