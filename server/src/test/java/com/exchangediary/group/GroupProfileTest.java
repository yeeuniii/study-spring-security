package com.exchangediary.group;

import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroupProfileTest {
    private static final String API_PATH = "/api/groups";
    @LocalServerPort
    private int port;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private GroupQueryService groupQueryService;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        memberRepository.deleteAllInBatch();
        groupRepository.deleteAllInBatch();
    }

    @Test
    void 프로필_이미지_선택_목록_조회_성공() {
        //Todo: 사용자 수정 api 구현 되면, 그룹 생성 api 와 같이 사용해서 테스트
        Group group = Group.builder()
                .build();
        groupRepository.save(group);
        Member member1 = createMember(group, 1);
        Member member2 = createMember(group, 2);
        memberRepository.saveAll(List.of(member1, member2));

        GroupProfileResponse groupProfileResponse =
                groupQueryService.viewSelectableProfileImage(group.getId());

        GroupProfileResponse Response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .when().get(API_PATH + "/" + group.getId() + "/profile-image")
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
                .when().get(API_PATH + "/" + groupId + "/profile-image")
                .then().log().all()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void 프로필_이미지_선택_목록_조회_멤버_초과() {
        Group group = Group.builder()
                .build();
        groupRepository.save(group);
        List<Member> members = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            members.add(createMember(group, i));
        }
        memberRepository.saveAll(members);

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .when().get(API_PATH + "/" + group.getId() + "/profile-image")
                .then().log().all()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    private Member createMember(Group group, int index) {
        return Member.builder()
                .profileLocation("resource/profile-Image" + index)
                .group(group)
                .build();
    }
}