package com.exchangediary.group.service;

import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupCommandService {
    private final GroupRepository groupRepository;

    public Group createGroup(String groupName) {
        Group group = Group.builder()
                .groupName(groupName)
                .numberOfMembers(0)
                .currentOrder(0)
                .build();
        return groupRepository.save(group);
    }
}
