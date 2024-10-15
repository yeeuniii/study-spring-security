package com.exchangediary.member.service;

import com.exchangediary.member.domain.MemberRepository;
import com.exchangediary.member.domain.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:truncate.sql"}, executionPhase = BEFORE_TEST_METHOD)
public class LoginTest {
    @Autowired
    private MemberRegistrationService memberRegistrationService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 기존_회원_로그인() {
        Long kakaoId = 1L;
        Member member = Member.builder().kakaoId(kakaoId).build();
        memberRepository.save(member);

        Long result = memberRegistrationService.getOrCreateMember(kakaoId).memberId();

        assertThat(result).isEqualTo(member.getId());
    }
}
