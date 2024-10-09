package com.exchangediary.group.api;

import com.exchangediary.ApiBaseTest;
import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.ui.dto.request.GroupNameRequest;
import com.exchangediary.group.ui.dto.response.GroupIdResponse;
import com.exchangediary.member.domain.entity.Member;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class GroupCreateApiTest extends ApiBaseTest {
    private static final String API_PATH = "/api/groups";
    @Autowired
    private GroupRepository groupRepository;

    @Test
    void 그룹_생성_성공() {
        String groupName = "버니즈";

        Long groupId = RestAssured
                .given().log().all()
                .body(new GroupNameRequest(groupName))
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when().post(API_PATH)
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(GroupIdResponse.class)
                .groupId();

        Group group = groupRepository.findById(groupId).get();
        assertThat(group.getName()).isEqualTo(groupName);
        Member groupCreator = memberRepository.findById(member.getId()).get();
        assertThat(groupCreator.getGroup().getId()).isEqualTo(group.getId());
    }
}
