package com.exchangediary.group;

import com.exchangediary.group.service.GroupCodeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GroupCodeServiceTest {
    @InjectMocks
    private GroupCodeService groupCodeService;

    @Test
    void 그룹_코드_생성() {
        String groupName = "버디즈";
        String encodedCode = groupCodeService.generateCode(groupName);

        String code = new String(Base64.getDecoder().decode(encodedCode));

        assertThat(code.substring(0, 3)).isEqualTo(groupName);
    }
}
