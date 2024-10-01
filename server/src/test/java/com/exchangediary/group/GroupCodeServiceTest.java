package com.exchangediary.group;

import com.exchangediary.group.service.GroupCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GroupCodeServiceTest {
    private static final String GROUP_NAME = "버니즈";
    @SpyBean
    private GroupCodeService groupCodeService;

    @Test
    void 그룹_코드_생성() {
        String groupName = "버디즈";
        String encodedCode = groupCodeService.generateCode(groupName);

        String code = new String(Base64.getDecoder().decode(encodedCode));

        assertThat(code.substring(0, 3)).isEqualTo(groupName);
    }
}
