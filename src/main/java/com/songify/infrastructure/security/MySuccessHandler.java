package com.songify.infrastructure.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class MySuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        setResponseCookie(response, oidcUser.getIdToken().getTokenValue());
        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl("https://localhost:3000");
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private void setResponseCookie(HttpServletResponse response, String tokenValue) {
        Cookie cookie = new Cookie("accessToken", tokenValue);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1 hour
        response.addCookie(cookie);
    }
}
