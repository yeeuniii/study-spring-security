package com.exchangediary.member.domain;

import com.exchangediary.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findBykakaoId(Long kakaoId);
    List<Member> findByGroupId(Long groupId);
}
