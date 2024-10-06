package com.exchangediary.group.service;

import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.ui.dto.response.GroupIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupCommandService {
    private final GroupRepository groupRepository;
    private final GroupCodeService groupCodeService;

    public GroupIdResponse createGroup(String groupName) {
        Group group = Group.of(groupName, groupCodeService.generateCode(groupName));
        groupRepository.save(group);
        return GroupIdResponse.from(group.getId());
    }
}
