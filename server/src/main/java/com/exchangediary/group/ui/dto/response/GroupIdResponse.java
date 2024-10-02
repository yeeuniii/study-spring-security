package com.exchangediary.group.ui.dto.response;

import com.exchangediary.group.domain.entity.Group;
import lombok.Builder;

@Builder
public record GroupIdResponse(
    Long groupId
) {
    public static GroupIdResponse of(Group group) {
        return GroupIdResponse.builder()
                .groupId(group.getId())
                .build();
    }
}
