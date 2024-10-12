package com.exchangediary.member.domain;

import com.exchangediary.group.domain.entity.Group;
import com.exchangediary.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findBykakaoId(Long kakaoId);
    List<Member> findAllByGroupOrderByOrderInGroup(Group group);
    List<Member> findAllByGroupId(Long groupId);
}
