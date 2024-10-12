package com.exchangediary.group.service;

import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.ConfilctException;
import com.exchangediary.global.exception.serviceexception.DuplicateException;
import com.exchangediary.global.exception.serviceexception.NotFoundException;
import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.ui.dto.response.GroupNicknameVerifyResponse;
import com.exchangediary.group.ui.dto.response.GroupMembersResponse;
import com.exchangediary.group.ui.dto.response.GroupProfileResponse;
import com.exchangediary.member.domain.MemberRepository;
import com.exchangediary.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupQueryService {
    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;

    public GroupMembersResponse listGroupMembersInfo(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.GROUP_NOT_FOUND,
                        "",
                        String.valueOf(groupId)
                ));
        List<Member> members = memberRepository.findAllByGroupOrderByOrderInGroup(group);
        return GroupMembersResponse.from(members);
    }

    public GroupProfileResponse viewSelectableProfileImage(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.GROUP_NOT_FOUND,
                        "",
                        String.valueOf(groupId))
                );
        List<Member> members = group.getMembers();
        checkNumberOfMembers(members.size());
        return GroupProfileResponse.from(members);
    }

    public static void checkNumberOfMembers(int numberOfMembers) {
        if (numberOfMembers >= 7) {
            throw new ConfilctException(
                    ErrorCode.FULL_MEMBERS_OF_GROUP,
                    "",
                    String.valueOf(numberOfMembers)
            );
        }
    }

    public GroupNicknameVerifyResponse verifyNickname(Long groupId, String nickname) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.DIARY_NOT_FOUND,
                        "",
                        String.valueOf(groupId))
                );
        boolean verification = isNicknameDuplicate(group.getMembers(), nickname);
        return GroupNicknameVerifyResponse.from(verification);
    }

    private boolean isNicknameDuplicate(List<Member> members, String nickname) {
        if (members.stream()
                .anyMatch(member -> member.getNickname().equals(nickname))) {
            throw new DuplicateException(
                    ErrorCode.NICKNAME_DUPLICATED,
                    "",
                    nickname
            );
        }
        return true;
    }
}
