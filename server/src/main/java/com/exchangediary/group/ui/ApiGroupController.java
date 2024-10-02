package com.exchangediary.group.ui;

import com.exchangediary.group.service.GroupCodeService;
import com.exchangediary.group.service.GroupQueryService;
import com.exchangediary.group.ui.dto.request.GroupCodeRequest;
import com.exchangediary.group.ui.dto.response.GroupIdResponse;
import com.exchangediary.group.ui.dto.response.GroupProfileImageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class ApiGroupController {
    private final GroupCodeService groupCodeService;
    private final GroupQueryService groupQueryService;

    @PostMapping("/code/verify")
    public ResponseEntity<GroupIdResponse> verifyGroupCode(
            @RequestBody @Valid GroupCodeRequest request
    ) {
        Long groupId = groupCodeService.verifyCode(request.code());
        GroupIdResponse response = GroupIdResponse.builder()
                .groupId(groupId)
                .build();
        return ResponseEntity
                .ok(response);
    }

    @GetMapping("/{groupId}/profileImage")
    public ResponseEntity<GroupProfileImageResponse> viewSelectableProfileImage(
            @PathVariable Long groupId) {
        GroupProfileImageResponse groupProfileImageResponse = groupQueryService.viewSelectableProfileImage(groupId);
        return ResponseEntity
                .ok()
                .body(groupProfileImageResponse);
    }
}
