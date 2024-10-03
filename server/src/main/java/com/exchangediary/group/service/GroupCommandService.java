package com.exchangediary.group.service;

import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.NotFoundException;
import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.ui.dto.request.GroupJoinRequest;
import com.exchangediary.group.ui.dto.response.GroupIdResponse;
import com.exchangediary.member.domain.MemberRepository;
import com.exchangediary.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void joinGroup(Long groupId, GroupJoinRequest request, Long memberId) {
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
        //그룹 생성인지 가입인지 매핑 된 수로 처리
        //프로필 이미지 이미 사용 된 경우 예외 처리
        //혹시 모르는 인원수 체크??
        member.joinGroupUpdate(request, group, member.getOrderInGroup() + 1);
        memberRepository.save(member);
    }
}
