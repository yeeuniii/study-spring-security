package com.exchangediary.group.ui;

import com.exchangediary.group.service.GroupCodeService;
import com.exchangediary.group.service.GroupQueryService;
import com.exchangediary.group.service.GroupCommandService;
import com.exchangediary.group.ui.dto.request.GroupCodeRequest;
import com.exchangediary.group.ui.dto.request.GroupJoinRequest;
import com.exchangediary.group.ui.dto.request.GroupNameRequest;
import com.exchangediary.group.ui.dto.response.GroupIdResponse;
import com.exchangediary.group.ui.dto.response.GroupJoinResponse;
import com.exchangediary.group.ui.dto.response.GroupProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class ApiGroupController {
    private final GroupCommandService groupCommandService;
    private final GroupCodeService groupCodeService;
    private final GroupQueryService groupQueryService;

    @PostMapping
    public ResponseEntity<GroupIdResponse> createGroup(
            @RequestBody @Valid GroupNameRequest request
    ) {
        GroupIdResponse groupIdResponse = groupCommandService.createGroup(request.groupName());
        return ResponseEntity
                .created(URI.create("/api/groups/" + groupIdResponse.groupId()))
                .body(groupIdResponse);
    }

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

    @GetMapping("/{groupId}/profile-image")
    public ResponseEntity<GroupProfileResponse> viewSelectableProfileImage(
            @PathVariable Long groupId) {
        GroupProfileResponse groupProfileResponse = groupQueryService.viewSelectableProfileImage(groupId);
        return ResponseEntity
                .ok()
                .body(groupProfileResponse);
    }

    @PatchMapping("/{groupId}/join")
    public ResponseEntity<GroupJoinResponse> joinGroup(
            @PathVariable Long groupId,
            @RequestBody @Valid GroupJoinRequest request
    ) {
        Long memberId = 1L;
        GroupJoinResponse response = groupCommandService.joinGroup(groupId, request, memberId);
        return ResponseEntity
                .ok()
                .body(response);
    }
}
