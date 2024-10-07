package com.exchangediary.global.config.web.interceptor;

import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.UnauthorizedException;
import com.exchangediary.member.domain.MemberRepository;
import com.exchangediary.member.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtAuthenticationInterceptor implements HandlerInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_TYPE = "Bearer ";
    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    public JwtAuthenticationInterceptor(
            JwtService jwtService,
            MemberRepository memberRepository
    ) {
        this.jwtService = jwtService;
        this.memberRepository = memberRepository;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        String token = extractJwtToken(request);

        if (jwtService.verifyToken(token)) {
            throw new UnauthorizedException(
                    ErrorCode.EXPIRED_TOKEN,
                    "",
                    token
            );
        }

        Long memberId = jwtService.extractMemberId(token);
        checkMemberExists(memberId);
        request.setAttribute("memberId", memberId);

        return true;
    }

    private String extractJwtToken(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if (authorization == null || authorization.indexOf(AUTHORIZATION_TYPE) != 0) {
            throw new UnauthorizedException(
                    ErrorCode.INVALID_AUTHORIZATION_TYPE,
                    "",
                    authorization
            );
        }
        return authorization.substring(AUTHORIZATION_TYPE.length());
    }

    private void checkMemberExists(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new UnauthorizedException(
                   ErrorCode.NOT_EXIST_MEMBER_TOKEN,
                   "",
                   String.valueOf(memberId)
                ));
    }
}
