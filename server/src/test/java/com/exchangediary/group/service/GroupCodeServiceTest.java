package com.exchangediary.group.service;

import com.exchangediary.global.exception.serviceexception.NotFoundException;
import com.exchangediary.group.domain.GroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:truncate.sql"}, executionPhase = BEFORE_TEST_METHOD)
public class GroupCodeServiceTest {
    private static final String GROUP_NAME = "버니즈";
    @Autowired
    private GroupCodeService groupCodeService;
    @Autowired
    private GroupCommandService groupCommandService;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    void 그룹_코드_생성() {
        String encodedCode = groupCodeService.generateCode(GROUP_NAME);

        String code = new String(Base64.getDecoder().decode(encodedCode));

        assertThat(code.substring(0, 3)).isEqualTo(GROUP_NAME);
    }

    @Test
    void 그룹_코드_검증_성공() {
        Long groupId = groupCommandService.createGroup(GROUP_NAME).groupId();
        String code = groupRepository.findById(groupId).get().getCode();

        Long result = groupCodeService.verifyCode(code);

        assertThat(result).isEqualTo(groupId);
    }

    @Test
    void 그룹_코드_검증_실패() {
        groupCommandService.createGroup(GROUP_NAME);
        String code = "invalid-code";

        NotFoundException exception = assertThrows(NotFoundException.class, () ->
            groupCodeService.verifyCode(code)
        );

        assertThat(exception.getValue()).isEqualTo(code);
    }

    @Test
    void 그룹_코드_반환_성공() {
        Long groupId = groupCommandService.createGroup(GROUP_NAME).groupId();
        String code = groupRepository.findById(groupId).get().getCode();

        String result = groupCodeService.findByGroupId(groupId);

        assertThat(result).isEqualTo(code);
    }

    @Test
    void 그룹_코드_반환_실패_그룹존재안함() {
        assertThrows(NotFoundException.class, () -> {
            groupCodeService.findByGroupId(1L);
        });
    }
}
