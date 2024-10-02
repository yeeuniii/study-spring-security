package com.exchangediary.group.service;

import com.exchangediary.global.exception.serviceexception.notfound.GroupNotFoundException;
import com.exchangediary.group.domain.GroupRepository;
import com.exchangediary.group.domain.entity.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupCodeService {
    private final GroupRepository groupRepository;

    public String generateCode(String groupName) {
        LocalDateTime today = LocalDateTime.now();
        String code = groupName + today + UUID.randomUUID();

        return Base64.getEncoder().encodeToString(code.getBytes());
    }

    public Long verifyCode(String code) {
        Group group = groupRepository.findByCode(code)
                .orElseThrow(() -> new GroupNotFoundException(code));

        return group.getId();
    }
}
