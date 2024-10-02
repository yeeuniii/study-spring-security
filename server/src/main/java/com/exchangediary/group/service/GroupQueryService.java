package com.exchangediary.group.service;

import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.ConfilctException;
import com.exchangediary.global.exception.serviceexception.NotFoundException;
import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
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

    public GroupProfileResponse viewSelectableProfileImage(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.DIARY_NOT_FOUND,
                        "",
                        String.valueOf(groupId))
                );
        checkNumberOfMembers(group.getNumberOfMembers());
        List<Member> members = memberRepository.findByGroupId(groupId);
        return GroupProfileResponse.from(members);
    }

    private void checkNumberOfMembers(int numberOfMembers) {
        if (numberOfMembers >= 7) {
            throw new ConfilctException(
                    ErrorCode.FULL_MEMBERS_OF_GROUP,
                    "",
                    String.valueOf(numberOfMembers)
                    );
        }
    }
}
