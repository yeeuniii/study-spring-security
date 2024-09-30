package com.exchangediary.group.service;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class GroupCodeService {
    public String generateCode() {
        // TODO: Group 엔티티 추가 시, group 매개변수로 받아서 code 생성하기
        Long groupId = 1L;
        String groupName = "group-name";
        String groupCreatedAt = "2024-09-23 12:34:12";

        String code = groupName + groupCreatedAt + groupId + groupId;

        return Base64.getEncoder().encodeToString(code.getBytes());
    }
}
