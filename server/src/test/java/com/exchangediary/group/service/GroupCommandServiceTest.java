package com.exchangediary.group.service;

import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
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
    @Autowired
    private GroupRepository groupRepository;

    @Test
    void 그룹_생성() {
        //given
        String groupName = "버디즈";
        String code = "asdf1234";

        when(groupCodeService.generateCode(groupName)).thenReturn(code);

        //when
        Long groupId = groupCommandService.createGroup(groupName).groupId();

        //then
        Group group = groupRepository.findById(groupId).get();
        assertThat(group.getName()).isEqualTo(groupName);
        assertThat(group.getCode()).isEqualTo(code);
        assertThat(group.getCurrentOrder()).isEqualTo(0);
        assertThat(group.getNumberOfMembers()).isEqualTo(0);
    }
}