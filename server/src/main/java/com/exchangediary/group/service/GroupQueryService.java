package com.exchangediary.group.service;

import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.invliadrange.InvalidRangeException;
import com.exchangediary.global.exception.serviceexception.notfound.GroupNotFoundException;
import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupQueryService {
    private final GroupRepository groupRepository;

    public GroupProfileImageResponse viewSelectableProfileImage(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(String.valueOf(groupId)));
        checkNumberOfMembers(group);
        return GroupProfileImageResponse.of(group);
    }

    private void checkNumberOfMembers(Group group) {
        int numberOfMembers = group.getNumberOfMembers();
        if (numberOfMembers >= 7) {
            throw new InvalidRangeException(String.valueOf(numberOfMembers),
                    ErrorCode.INVALID_MEMBERS_RANGE);
        }
    }
}
