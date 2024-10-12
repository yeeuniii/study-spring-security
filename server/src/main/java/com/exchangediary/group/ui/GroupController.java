package com.exchangediary.group.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupController {
    @GetMapping("/group")
    public String createOrJoinGroup() {
        return "group-page";
    }
}
