package com.exchangediary.group;

import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.service.GroupQueryService;
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
class GroupNicknameTest {
    private static final String API_PATH = "/api/groups";
    @LocalServerPort
    private int port;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        memberRepository.deleteAllInBatch();
        groupRepository.deleteAllInBatch();
    }

    @Test
    void 닉네임_유효성_검사_성공() {
        Group group = Group.builder()
                .build();
        groupRepository.save(group);

        RestAssured
                .given()
                .queryParam("nickname", "jisunggi")
                .when().get(API_PATH + "/" + group.getId() + "/nickname/verify")
                .then()
                .statusCode(200)
                .body("verification", equalTo(true));
    }

    @Test
    void 닉네임_유효성_검사_중복() {
        Group group = Group.builder()
                .build();
        groupRepository.save(group);
        Member member = Member.builder()
                .nickname("jisunggi")
                .group(group)
                .build();
        memberRepository.save(member);

        RestAssured
                .given()
                .queryParam("nickname", "jisunggi")
                .when().get(API_PATH + "/" + group.getId() + "/nickname/verify")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}