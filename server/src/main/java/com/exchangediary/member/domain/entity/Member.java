package com.exchangediary.member.domain.entity;

import com.exchangediary.global.domain.entity.BaseEntity;
import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.group.ui.dto.request.GroupJoinRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED, force = true)
@AllArgsConstructor(access = PRIVATE)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private final Long kakaoId;
    private String nickname;
    private String profileLocation;
    private Integer orderInGroup;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public void updateMemberForGroupJoin(GroupJoinRequest request, Group group, int orderInGroup) {
        this.nickname = request.nickname();
        this.profileLocation = request.profileLocation();
        this.orderInGroup = orderInGroup;
        this.group = group;
    }
}
