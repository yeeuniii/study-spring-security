package com.exchangediary.group.api;

import com.exchangediary.BaseTest;
import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.service.GroupCommandService;
import com.exchangediary.group.service.GroupQueryService;
import com.exchangediary.group.ui.dto.response.GroupProfileResponse;
import com.exchangediary.member.domain.MemberRepository;
import com.exchangediary.member.domain.entity.Member;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

class GroupProfileApiTest extends BaseTest {
    private static final String GROUP_NAME = "버니즈";
    private static final String API_PATH = "/api/groups/%d/profile-image";
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupQueryService groupQueryService;
    @Autowired
    private GroupCommandService groupCommandService;

    @Test
    void 프로필_이미지_선택_목록_조회_성공() {
        Group group = createGroup();
        groupRepository.save(group);
        Member member1 = createMember(group, 1);
        Member member2 = createMember(group, 2);
        memberRepository.saveAll(List.of(member1, member2));

        GroupProfileResponse groupProfileResponse =
                groupQueryService.viewSelectableProfileImage(group.getId());

        GroupProfileResponse Response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when().get(String.format(API_PATH, group.getId()))
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract().as(GroupProfileResponse.class);

        assertThat(Response).isEqualTo(groupProfileResponse);
    }

    @Test
    void 프로필_이미지_선택_목록_조회_그룹없음() {
        Long groupId = 100L;

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when().get(String.format(API_PATH, groupId))
                .then().log().all()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void 프로필_이미지_선택_목록_조회_멤버_초과() {
        Group group = createGroup();
        groupRepository.save(group);
        List<Member> members = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            members.add(createMember(group, i));
        }
        memberRepository.saveAll(members);

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when().get(String.format(API_PATH, group.getId()))
                .then().log().all()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    private Member createMember(Group group, int index) {
        return Member.builder()
                .profileLocation("resource/profile-Image" + index)
                .kakaoId(1234L + index)
                .group(group)
                .build();
    }

    private Group createGroup() {
        return Group.builder()
                .name(GROUP_NAME)
                .currentOrder(0)
                .code("code")
                .build();
    }
}