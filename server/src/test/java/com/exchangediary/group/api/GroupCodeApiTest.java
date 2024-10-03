package com.exchangediary.group.api;

import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.service.GroupCommandService;
import com.exchangediary.group.ui.dto.request.GroupCodeRequest;
import com.exchangediary.group.ui.dto.response.GroupIdResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GroupCodeApiTest {
    private static final String API_PATH = "/api/groups/code/verify";
    @LocalServerPort
    private int port;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupCommandService groupCommandService;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        groupRepository.deleteAllInBatch();
    }

    /* TODO: 시나리오 따라서 테스트 수정
     * 1. 그룹 생성
     * 2. 사용자 정보 수정 -> 그룹 코드 반환
     * 3. 그룹 코드 유효성 검사
     */
    @Test
    void 그룹_코드_유효성_검증_성공() {
        String groupName = "버니즈";
        Long groupId = groupCommandService.createGroup(groupName).groupId();
        Group group = groupRepository.findById(groupId).get();
        GroupCodeRequest groupCodeRequest = new GroupCodeRequest(group.getCode());

        GroupIdResponse response = RestAssured
                .given().log().all()
                .body(groupCodeRequest)
                .contentType(ContentType.JSON)
                .when().post(API_PATH)
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract().as(GroupIdResponse.class);

        assertThat(response.groupId()).isEqualTo(group.getId());
    }

    @Test
    void 그룹_코드_유효성_검증_실패() {
        String groupName = "버니즈";
        Long groupId = groupCommandService.createGroup(groupName).groupId();
        Group group = groupRepository.findById(groupId).get();
        GroupCodeRequest groupCodeRequest = new GroupCodeRequest(group.getCode() + "invalid");

        RestAssured
                .given().log().all()
                .body(groupCodeRequest)
                .contentType(ContentType.JSON)
                .when().post(API_PATH)
                .then().log().all()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void 그룹_코드_유효성_검증_실패_빈코드() {
        GroupCodeRequest groupCodeRequest = new GroupCodeRequest("");

        RestAssured
                .given().log().all()
                .body(groupCodeRequest)
                .contentType(ContentType.JSON)
                .when().post(API_PATH)
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
