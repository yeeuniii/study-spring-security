package com.exchangediary.group.service;

import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.DuplicateException;
import com.exchangediary.global.exception.serviceexception.NotFoundException;
import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.ui.dto.request.GroupJoinRequest;
import com.exchangediary.group.ui.dto.response.GroupIdResponse;
import com.exchangediary.group.ui.dto.response.GroupJoinResponse;
import com.exchangediary.member.domain.MemberRepository;
import com.exchangediary.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupCommandService {
    private final GroupRepository groupRepository;
    private final GroupCodeService groupCodeService;
    private final MemberRepository memberRepository;

    public GroupIdResponse createGroup(String groupName, Member member) {
        Group group = Group.of(groupName, groupCodeService.generateCode(groupName));
        groupRepository.save(group);
        member.addGroup(group);
        memberRepository.save(member);
        return GroupIdResponse.from(group.getId());
    }

    public GroupJoinResponse joinGroup(Long groupId, GroupJoinRequest request, Long memberId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.GROUP_NOT_FOUND,
                        "",
                        String.valueOf(groupId))
                );
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.MEMBER_NOT_FOUND,
                        "",
                        String.valueOf(memberId))
                );
        String code = processGroupJoinOrCreate(group, request.profileLocation());
        int maxOrderInGroup = findMaxOrderInGroup(group.getMembers());
        member.updateMemberGroupInfo(request, group, maxOrderInGroup + 1);
        memberRepository.save(member);
        return GroupJoinResponse.from(code);
    }

    private int findMaxOrderInGroup(List<Member> members) {
        return members.stream()
                .mapToInt(Member::getOrderInGroup)
                .max()
                .orElse(0);
    }

    private String processGroupJoinOrCreate(Group group, String profileLocation)
    {
        List<Member> members = group.getMembers();
        if (members.isEmpty()) {
            return group.getCode();
        }
        checkProfileDuplicate(members, profileLocation);
        GroupQueryService.checkNumberOfMembers(members.size());
        return null;
    }

    private void checkProfileDuplicate(List<Member> members, String profileLocation) {
        if (members.stream()
                .anyMatch(member -> member.getProfileLocation().equals(profileLocation))) {
            throw new DuplicateException(
                    ErrorCode.PROFILE_DUPLICATED,
                    "",
                    profileLocation
            );
        }
    }
}
