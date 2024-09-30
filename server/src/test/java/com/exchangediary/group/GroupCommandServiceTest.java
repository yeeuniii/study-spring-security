package com.exchangediary.group;

import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.service.GroupCodeService;
import com.exchangediary.group.service.GroupCommandService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroupCommandServiceTest {
    @Autowired
    private GroupCommandService groupCommandService;
    @MockBean
    private GroupCodeService groupCodeService;

    @Test
    void 그룹_생성() {
        //given
        String groupName = "버디즈";
        String code = "asdf1234";

        when(groupCodeService.generateCode(groupName)).thenReturn(code);

        //when
        Group createdGroup = groupCommandService.createGroup(groupName);

        //then
        assertThat(createdGroup.getName()).isEqualTo(groupName);
        assertThat(createdGroup.getCode()).isEqualTo(code);
        assertThat(createdGroup.getNumberOfMembers()).isEqualTo(0);
        assertThat(createdGroup.getCurrentOrder()).isEqualTo(0);
    }

}