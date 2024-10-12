package com.exchangediary.group.service;

import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.ui.dto.request.GroupJoinRequest;
import com.exchangediary.group.ui.dto.response.GroupJoinResponse;
import com.exchangediary.member.domain.MemberRepository;
import com.exchangediary.member.domain.entity.Member;
import com.exchangediary.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupJoinService {
    private final GroupQueryService groupQueryService;
    private final GroupValidationService groupValidationService;
    private final MemberQueryService memberQueryService;
    private final MemberRepository memberRepository;

    public GroupJoinResponse joinGroup(Long groupId, GroupJoinRequest request, Long memberId) {
        Group group = groupQueryService.findGroup(groupId);
        Member member = memberQueryService.findMember(memberId);

        String code = processGroupJoinOrCreate(group, request.profileLocation());
        int maxOrderInGroup = findMaxOrderInGroup(group.getMembers());
        member.updateMemberGroupInfo(request, group, maxOrderInGroup + 1);
        memberRepository.save(member);
        return GroupJoinResponse.from(code);
    }

    private String processGroupJoinOrCreate(Group group, String profileLocation) {
        List<Member> members = group.getMembers();

        if (members.isEmpty()) {
            return group.getCode();
        }
        groupValidationService.checkProfileDuplicate(members, profileLocation);
        groupValidationService.checkNumberOfMembers(members.size());
        return null;
    }

    private int findMaxOrderInGroup(List<Member> members) {
        return members.stream()
                .mapToInt(Member::getOrderInGroup)
                .max()
                .orElse(0);
    }
}
