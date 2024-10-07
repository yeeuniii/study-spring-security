package com.exchangediary.group.api;

import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.service.GroupCommandService;
import com.exchangediary.member.domain.MemberRepository;
import com.exchangediary.member.domain.entity.Member;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.core.IsEqual.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroupNicknameApiTest {
    private static final String GROUP_NAME = "버니즈";
    private static final String API_PATH = "/api/groups/%d/nickname/verify";
    @LocalServerPort
    private int port;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private GroupCommandService groupCommandService;


    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        memberRepository.deleteAllInBatch();
        groupRepository.deleteAllInBatch();
    }

    @Test
    void 닉네임_유효성_검사_성공() {
        Long groupId = groupCommandService.createGroup(GROUP_NAME).groupId();

        RestAssured
                .given().log().all()
                .queryParam("nickname", "jisunggi")
                .when().get(String.format(API_PATH, groupId))
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("verification", equalTo(true));
    }

    @Test
    void 닉네임_유효성_검사_중복() {
        Long groupId = groupCommandService.createGroup(GROUP_NAME).groupId();
        Group group = groupRepository.findById(groupId).orElse(null);
        Member member = Member.builder()
                .nickname("jisunggi")
                .kakaoId(12345L)
                .group(group)
                .build();
        memberRepository.save(member);

        RestAssured
                .given().log().all()
                .queryParam("nickname", "jisunggi")
                .when().get(String.format(API_PATH, groupId))
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}