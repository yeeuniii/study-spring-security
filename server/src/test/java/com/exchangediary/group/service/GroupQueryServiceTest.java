package com.exchangediary.group.service;

import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.ui.dto.response.GroupMembersResponse;
import com.exchangediary.member.domain.MemberRepository;
import com.exchangediary.member.domain.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:truncate.sql"}, executionPhase = BEFORE_TEST_METHOD)
public class GroupQueryServiceTest {
    @Autowired
    private GroupQueryService groupQueryService;
    @Autowired
    private GroupCommandService groupCommandService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private GroupRepository groupRepository;

    private Member makeMember(Group group, int order) {
        return Member.builder()
                .group(group)
                .nickname("nickname" + order)
                .orderInGroup(order)
                .build();
    }

    @Test
    void 그룹원_정보_리스트_조회_성공() {
        Long groupId = groupCommandService.createGroup("버니즈").groupId();
        Group group = groupRepository.findById(groupId).get();
        memberRepository.saveAll(Arrays.asList(makeMember(group, 1), makeMember(group, 2)));
        GroupMembersResponse response = groupQueryService.listGroupMembersInfo(groupId);

        assertThat(response.members()).hasSize(2);
    }
}
