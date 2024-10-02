package com.exchangediary.group.ui;

import com.exchangediary.group.service.GroupCodeService;
import com.exchangediary.group.ui.dto.request.GroupCodeRequest;
import com.exchangediary.group.ui.dto.response.GroupIdResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class ApiGroupController {
    private final GroupCodeService groupCodeService;

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
}
