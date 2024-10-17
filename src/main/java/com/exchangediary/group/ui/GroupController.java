package com.exchangediary.group.ui;

import com.exchangediary.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {
    private final MemberQueryService memberQueryService;

    @GetMapping
    public String createOrJoinGroup(
            @RequestAttribute Long memberId
    ) {
        Optional<Long> groupId = memberQueryService.findGroupBelongTo(memberId);
        if (groupId.isPresent()) {
            return "redirect:/group/" + groupId.get();
        }
        return "group/group-page";
    }

    @GetMapping("/{groupId}")
    public String showCalendar(@PathVariable Long groupId) {
        // 인가 거쳤다고 가정
        return "group/group-calendar";
    }
}
