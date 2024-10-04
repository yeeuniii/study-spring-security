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

    public GroupIdResponse createGroup(String groupName) {
        Group group = Group.of(groupName, groupCodeService.generateCode(groupName));
        groupRepository.save(group);
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
        String status = processGroupJoinOrCreate(groupId, request.profileLocation());
        int maxOrderInGroup = memberRepository.findMaxOrderInGroupByGroupId(groupId);
        member.joinGroupUpdate(request, group, maxOrderInGroup + 1);
        memberRepository.save(member);
        return GroupJoinResponse.from(status);
    }

    private String processGroupJoinOrCreate(Long groupId, String profileLocation)
    {
        List<Member> members = memberRepository.findAllByGroupId(groupId);
        if (members.isEmpty()) {
            return "그룹 생성";
        }
        else {
            isProfileDuplicate(groupId, profileLocation);
            GroupQueryService.checkNumberOfMembers(members.size());
            return "그룹 가입";
        }
    }

    private void isProfileDuplicate(Long groupId, String profileLocation) {
        if (memberRepository.existsByGroupIdAndProfileLocation(groupId, profileLocation)) {
            throw new DuplicateException(
                    ErrorCode.PROFILE_DUPLICATED,
                    "",
                    profileLocation
            );
        }
    }
}
