package com.exchangediary.group;

import com.exchangediary.global.exception.serviceexception.NotFoundException;
import com.exchangediary.group.service.GroupCodeService;
import com.exchangediary.group.service.GroupCommandService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GroupCodeServiceTest {
    private static final String GROUP_NAME = "버니즈";
    @SpyBean
    private GroupCodeService groupCodeService;
    @Autowired
    private GroupCommandService groupCommandService;

    @Test
    void 그룹_코드_생성() {
        String encodedCode = groupCodeService.generateCode(GROUP_NAME);

        String code = new String(Base64.getDecoder().decode(encodedCode));

        assertThat(code.substring(0, 3)).isEqualTo(GROUP_NAME);
    }

    @Test
    void 그룹_코드_검증_성공() {
        String code = "valid-code";
        when(groupCodeService.generateCode(any(String.class))).thenReturn(code);
        Long groupId = groupCommandService.createGroup(GROUP_NAME).groupId();

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
}
