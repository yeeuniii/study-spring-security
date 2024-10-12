package com.exchangediary.global.config.web.interceptor;

import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.UnauthorizedException;
import com.exchangediary.member.service.CookieService;
import com.exchangediary.member.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtAuthenticationInterceptor implements HandlerInterceptor {
    private static final String COOKIE_NAME = "token";
    private final JwtService jwtService;
    private final CookieService cookieService;

    public JwtAuthenticationInterceptor(
            JwtService jwtService,
            CookieService cookieService
    ) {
        this.jwtService = jwtService;
        this.cookieService = cookieService;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        String token = extractJwtToken(request);

        if (!jwtService.verifyToken(token)) {
            throw new UnauthorizedException(
                    ErrorCode.EXPIRED_TOKEN,
                    "",
                    token
            );
        }

        Long memberId = jwtService.extractMemberId(token);
        request.setAttribute("memberId", memberId);

        return true;
    }

    private String extractJwtToken(HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();

            return cookieService.getValueFromCookies(cookies, COOKIE_NAME);
        } catch (RuntimeException exception) {
            throw new UnauthorizedException(
                    ErrorCode.NEED_TO_REQUEST_TOKEN,
                    "",
                    COOKIE_NAME
            );
        }
    }
}
