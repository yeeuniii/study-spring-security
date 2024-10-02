package com.exchangediary.group.service;

import com.exchangediary.group.domain.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupQueryService {
    private final GroupRepository groupRepository;

    public void viewSelectableProfileImage() {

    }

    private void checkNumberOfMembers() {

    }
}
