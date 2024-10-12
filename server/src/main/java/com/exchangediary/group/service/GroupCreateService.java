package com.exchangediary.group.service;

import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.ui.dto.response.GroupIdResponse;
import com.exchangediary.member.domain.MemberRepository;
import com.exchangediary.member.domain.entity.Member;
import com.exchangediary.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupCreateService {
    private final GroupCodeService groupCodeService;
    private final GroupRepository groupRepository;
    private final MemberQueryService memberQueryService;
    private final MemberRepository memberRepository;

    public GroupIdResponse createGroup(String groupName, Long memberId) {
        Group group = saveGroup(groupName);
        updateMember(memberId, group);
        return GroupIdResponse.from(group.getId());
    }

    private Group saveGroup(String groupName) {
        Group group = Group.of(groupName, groupCodeService.generateCode(groupName));
        return groupRepository.save(group);
    }

    private void updateMember(Long memberId, Group group) {
        Member member = memberQueryService.findMember(memberId);
        member.addGroup(group);
        memberRepository.save(member);
    }
}
