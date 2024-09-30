package com.exchangediary.group.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
public class GroupCodeService {
    public String generateCode(String groupName) {
        LocalDateTime today = LocalDateTime.now();
        String code = groupName + today + UUID.randomUUID();

        return Base64.getEncoder().encodeToString(code.getBytes());
    }
}
