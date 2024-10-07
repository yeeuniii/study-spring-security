package com.exchangediary.group.api;

import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.NotFoundException;
import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.service.GroupCommandService;
import com.exchangediary.group.ui.dto.request.GroupJoinRequest;
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
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:truncate.sql"}, executionPhase = BEFORE_TEST_METHOD)
class GroupJoinApiTest {
    private static final String GROUP_NAME = "버니즈";
    private static final String API_PATH = "/api/groups/%d/join/%d";
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
//        memberRepository.deleteAllInBatch();
//        groupRepository.deleteAllInBatch();
    }

    @Test
    void 그룹_가입_성공 () {
        Long groupId = groupCommandService.createGroup(GROUP_NAME).groupId();
        Group group = groupRepository.findById(groupId).orElse(null);
        Member groupMember = Member.builder()
                .kakaoId(12345L)
                .orderInGroup(1)
                .profileLocation("resource/image1")
                .group(group)
                .build();
        Member newMember = Member.builder()
                .kakaoId(12345L)
                .orderInGroup(0)
                .build();
        memberRepository.saveAll(Arrays.asList(groupMember, newMember));
        GroupJoinRequest request = new GroupJoinRequest("resource/image2", "jisunggi");

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .patch(String.format(API_PATH, groupId, newMember.getId()))
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("code", equalTo("null"));

        Member updatedMember = memberRepository.findById(newMember.getId())
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.MEMBER_NOT_FOUND,
                        "",
                        String.valueOf(newMember.getId())
                ));
        assertThat(updatedMember.getNickname()).isEqualTo("jisunggi");
        assertThat(updatedMember.getProfileLocation()).isEqualTo("resource/image2");
        assertThat(updatedMember.getOrderInGroup()).isEqualTo(2);
        assertThat(updatedMember.getGroup().getId()).isEqualTo(group.getId());
    }

    @Test
    void 그룹_생성_후_가입_성공 () {
        Long groupId = groupCommandService.createGroup(GROUP_NAME).groupId();
        Group group = groupRepository.findById(groupId).orElse(null);
        Member member = Member.builder()
                .kakaoId(12345L)
                .orderInGroup(0)
                .build();
        memberRepository.save(member);
        GroupJoinRequest request = new GroupJoinRequest("resource/image1", "jisunggi");

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .patch(String.format(API_PATH, groupId, member.getId()))
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("code", equalTo(group.getCode()));

        Member updatedMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.MEMBER_NOT_FOUND,
                        "",
                        String.valueOf(member.getId())
                ));
        assertThat(updatedMember.getNickname()).isEqualTo("jisunggi");
        assertThat(updatedMember.getProfileLocation()).isEqualTo("resource/image1");
        assertThat(updatedMember.getOrderInGroup()).isEqualTo(1);
        assertThat(updatedMember.getGroup().getId()).isEqualTo(group.getId());
    }

    @Test
    void 프로필_중복_그룹_가입_실패() {
        Long groupId = groupCommandService.createGroup(GROUP_NAME).groupId();
        Group group = groupRepository.findById(groupId).orElse(null);
        Member groupMember = Member.builder()
                .kakaoId(12345L)
                .orderInGroup(1)
                .profileLocation("resource/image1")
                .group(group)
                .build();
        Member newMember = Member.builder()
                .kakaoId(12345L)
                .orderInGroup(0)
                .build();
        memberRepository.saveAll(Arrays.asList(groupMember, newMember));
        GroupJoinRequest request = new GroupJoinRequest("resource/image1", "jisunggi");

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .patch(String.format(API_PATH, groupId, newMember.getId()))
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("이미 선택된 캐릭터입니다."));
    }
}